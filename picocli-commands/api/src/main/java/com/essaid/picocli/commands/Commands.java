package com.essaid.picocli.commands;

import com.essaid.picocli.commands.factory.PicoCollectionFactory;
import com.essaid.picocli.commands.factory.PicoGroovyFactory;
import com.essaid.picocli.commands.factory.PicoInstanceFactory;
import com.essaid.picocli.commands.factory.PicoMapFactory;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class Commands implements ICommands.IICommands {
  
  private final String name;
  private final Map<String, List<ICommandType>> typesByPath = new ConcurrentHashMap<>();
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  
  static CommandLine.IFactory getDefaultFactory() {
    return new PicoGroovyFactory(new PicoCollectionFactory(new PicoMapFactory(new PicoInstanceFactory())));
  }
  
  Commands(String instanceName) {
    this.name = instanceName;
    addCommandType(null, DEFAULT_ROOT_COMMAND_PATH, DefaultRootCommand.class, getDefaultFactory(), null);
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public ICommandType addCommandType(String name, String path, Class<?> commandClass, CommandLine.IFactory factory,
                                     String info) {
    if (factory == null) {
      factory = getDefaultFactory();
    }
    ICommandType type = new CommandType(name, path, commandClass, factory, this, info);
    typesByPath.computeIfAbsent(path, s -> new ArrayList<>()).add(type);
    typesList.add(type);
    return type;
  }
  
  @Override
  public void removeCommandType(ICommandType commandType) {
    typesList.remove(commandType);
    Iterator<String> pathIterator = typesByPath.keySet().iterator();
    while (pathIterator.hasNext()) {
      String path = pathIterator.next();
      List<ICommandType> pathCommandTypes = typesByPath.get(path);
      pathCommandTypes.remove(commandType);
      if (pathCommandTypes.isEmpty()) {
        pathIterator.remove();
      }
    }
  }
  
  @Override
  public CommandLineConfig getDefaultCommandLineConfig() {
    List<CommandLineConfig> allRootCommandLineConfigs = getAllTopCommandLineConfigs(true);
    CommandLineConfig rootCommandConfig = new CommandLineConfig(null, DEFAULT_ROOT_COMMAND_PATH, false, false);
    rootCommandConfig.getSubCommands().addAll(allRootCommandLineConfigs);
    return resolveCommandLineConfig(rootCommandConfig);
  }
  
  @Override
  public CommandLineConfig resolveCommandLineConfig(CommandLineConfig commandLineConfig) {
    
    String path = commandLineConfig.getPath();
    List<ICommandType> commandTypes = getCommandTypesByPath().get(path);
    CommandLine commandLine = null;
    
    if (commandTypes != null) {
      ICommandType commandType = commandTypes.get(0);
      commandLine = new CommandLine(commandType.getCommandClass(), commandType.getFactory());
      commandLineConfig.setCommandType(commandType);
      commandLineConfig.setCommandLine(commandLine);
      if (commandLineConfig.isAddRecursive()) {
        addCommandLineRecursive(commandType, commandLine);
      }
    } else {
      //TODO
    }
    
    for (CommandLineConfig childConfig : commandLineConfig.getSubCommands()) {
      resolveCommandLineConfig(childConfig);
      String newName = childConfig.getName();
      newName = newName != null ? newName : childConfig.getCommandType().getName();
      commandLine.addSubcommand(newName, childConfig.getCommandLine());
    }
    return commandLineConfig;
  }
  
  private void addCommandLineRecursive(ICommandType commandType, CommandLine commandLine) {
    commandType.internal()
        .getDirectChildPaths()
        .stream()
        .filter(path -> path.contains(path))
        .map(path -> typesByPath.get(path).get(0))
        .forEach(ct -> {
          CommandLine childCommandLine = new CommandLine(ct.getCommandClass(), ct.getFactory());
          commandLine.addSubcommand(ct.getName(), childCommandLine);
          addCommandLineRecursive(ct, childCommandLine);
        });
  }
  
  
  @Override
  public IICommands internal() {
    return this;
  }
  
  @Override
  public Map<String, List<ICommandType>> getCommandTypesByPath() {
    return typesByPath;
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
  
  private List<CommandLineConfig> getAllTopCommandLineConfigs(boolean setConfigRecursive) {
    List<CommandLineConfig> topCommands = typesByPath.entrySet()
        .stream()
        .filter(entry -> !entry.getKey().startsWith("_") && entry.getValue().get(0).internal().isTopCommand())
        .map(entry -> new CommandLineConfig(null, entry.getValue().get(0).getPath(), setConfigRecursive, false))
        .collect(Collectors.toList());
    
    return topCommands;
  }
}
