package com.essaid.picocli.commands;

import com.essaid.picocli.commands.command.CommandLineConfig;
import com.essaid.picocli.commands.factory.PicoCollectionFactory;
import com.essaid.picocli.commands.factory.PicoGroovyFactory;
import com.essaid.picocli.commands.factory.PicoInstanceFactory;
import com.essaid.picocli.commands.factory.PicoMapFactory;
import com.essaid.picocli.commands.type.CommandType;
import com.essaid.picocli.commands.type.ICommandType;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class Commands implements ICommands.IICommands {
  
  private final String name;
  private final Map<String, List<ICommandType>> typesByPath = new ConcurrentHashMap<>();
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  
  Commands(String instanceName) {
    this.name = instanceName;
  }
  
  static CommandLine.IFactory getDefaultFactory() {
    return new PicoGroovyFactory(new PicoCollectionFactory(new PicoMapFactory(new PicoInstanceFactory())));
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public ICommandType addCommandType(ICommands commands, String name, String path, int order, String title,
                                     String shortDescription, String longDescription, String notes,
                                     Class<?> commandClass, CommandLine.IFactory factory,
                                     CommandLine.IExecutionStrategy strategy) {
    
    ICommandType type = new CommandType(commands, name, path, order, title, shortDescription, longDescription, notes,
        commandClass, factory, strategy);
    List<ICommandType> typeList = typesByPath.computeIfAbsent(path, pathKey -> new ArrayList<>());
    typeList.add(type);
    Collections.sort(typeList);
    return type;
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
      commandLine = new CommandLine(commandType.getCommandClass(), commandType.getCommandFactory());
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
          CommandLine childCommandLine = new CommandLine(ct.getCommandClass(), ct.getCommandFactory());
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
