package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface ICommands2 {
  
  String DEFAULT_ROOT_COMMAND_PATH = "_default-root-command";
  
  static ICommands2 getInstance(String instanceName) {
    return IICommands2.INSTANCES.get(instanceName);
  }
  
  static ICommands2 getOrCreateInstance(String instanceName) {
    return IICommands2.INSTANCES.computeIfAbsent(instanceName, s -> new Commands(s));
  }
  
  static ICommands2 removeInstance(String instanceName) {
    return IICommands2.INSTANCES.remove(instanceName);
  }
  
  String getName();
  
  ICommandType2 addCommandType(String name, String path, ICommandFactory2 factory, String info);
  
  void removeCommandType(ICommandType2 commandType);
  
  
  /**
   * This will add all the {@link ICommandType2} that do not have a "parent" command in the paths.
   *
   * @return
   */
  CommandLineConfig getDefaultCommandLineConfig();
  
  public CommandLineConfig resolveCommandLineConfig(CommandLineConfig commandLineConfig);
  
  
  IICommands2 internal();
  
  interface IICommands2 extends ICommands2 {
    static String DEFAULT_INSTANCE_NAME = "_default";
    
    static Map<String, IICommands2> INSTANCES = new ConcurrentHashMap<>();
    
    
    Map<String, List<ICommandType2>> getCommandTypesByPath();
    
    List<ICommandType2> typesList = new ArrayList<>();
    
    Map<Object, Object> getContext();
  }
}

class Commands implements ICommands2.IICommands2 {
  
  private final String name;
  private final Map<String, List<ICommandType2>> typesByPath = new ConcurrentHashMap<>();
  
  Commands(String instanceName) {
    this.name = instanceName;
    addCommandType(null, DEFAULT_ROOT_COMMAND_PATH, new ClasspathCommandFactory(DefaultRootCommand.class), null);
    typesByPath.computeIfAbsent(DEFAULT_ROOT_COMMAND_PATH, s -> new ArrayList<>()).add(null);
    typesByPath.put(DEFAULT_ROOT_COMMAND_PATH, null);
  }
  
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public ICommandType2 addCommandType(String name, String path, ICommandFactory2 factory, String info) {
    ICommandType2 type = new CommandType(name, path, factory, this, info);
    typesByPath.computeIfAbsent(path, s -> new ArrayList<>()).add(type);
    typesList.add(type);
    return type;
  }
  
  @Override
  public void removeCommandType(ICommandType2 commandType) {
    typesList.remove(commandType);
    Iterator<String> pathIterator = typesByPath.keySet().iterator();
    while (pathIterator.hasNext()) {
      String path = pathIterator.next();
      List<ICommandType2> pathCommandTypes = typesByPath.get(path);
      pathCommandTypes.remove(commandType);
      if (pathCommandTypes.isEmpty()) {
        pathIterator.remove();
      }
    }
  }
  
  @Override
  public CommandLineConfig getDefaultCommandLineConfig() {
    List<CommandLineConfig> allRootCommandLineConfigs = getAllRootCommandLineConfigs(true);
    CommandLineConfig rootCommandConfig = new CommandLineConfig(null, DEFAULT_ROOT_COMMAND_PATH, false, false);
    rootCommandConfig.getSubCommands().addAll(allRootCommandLineConfigs);
    return resolveCommandLineConfig(rootCommandConfig);
  }
  
  @Override
  public CommandLineConfig resolveCommandLineConfig(CommandLineConfig commandLineConfig) {
    
    String path = commandLineConfig.getPath();
    List<ICommandType2> commandTypes = getCommandTypesByPath().get(path);
    CommandLine rootCommandLine = null;
    
    if (commandTypes != null) {
      ICommandType2 commandType = commandTypes.get(0);
      commandLineConfig.setCommandType(commandType);
      rootCommandLine = new CommandLine(commandType.getClass(), commandType.getFactory());
      if (commandLineConfig.isAddRecursive()) {
        addCommandLineRecursive(commandType, rootCommandLine);
      }
    } else {
      //TODO
    }
    
    for(CommandLineConfig childConfig: commandLineConfig.getSubCommands()){
      resolveCommandLineConfig(childConfig);
      String newName = childConfig.getName();
      newName = newName != null? newName : childConfig.getCommandType().getName();
      rootCommandLine.addSubcommand( newName , childConfig.getCommandLine());
    }
    return commandLineConfig;
  }
  
  private void addCommandLineRecursive(ICommandType2 commandType, CommandLine commandLine) {
    commandType.internal().getDirectChildPaths().stream().map(path -> typesByPath.get(path).get(0)).forEach(ct -> {
      CommandLine childCommandLine = new CommandLine(ct.getClass(), ct.getFactory());
      commandLine.addSubcommand(ct.getName(), childCommandLine);
      addCommandLineRecursive(ct, childCommandLine);
    });
  }
  
  
  @Override
  public IICommands2 internal() {
    return this;
  }
  
  @Override
  public Map<String, List<ICommandType2>> getCommandTypesByPath() {
    return typesByPath;
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
  
  private List<CommandLineConfig> getAllRootCommandLineConfigs(boolean recursive) {
    List<CommandLineConfig> rootConfigs = typesList.stream()
        .filter(ct -> !ct.internal().isDirectParentPathCommandType())
        .map(commandType2 -> new CommandLineConfig(null, commandType2.getPath(), recursive, false))
        .collect(Collectors.toList());
    return rootConfigs;
  }
}
