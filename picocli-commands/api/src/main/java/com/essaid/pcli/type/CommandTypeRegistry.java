package com.essaid.pcli.type;

import com.essaid.pcli.factory.PicoCollectionFactory;
import com.essaid.pcli.factory.PicoGroovyFactory;
import com.essaid.pcli.factory.PicoInstanceFactory;
import com.essaid.pcli.factory.PicoMapFactory;
import com.essaid.util.asserts.Asserts;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class CommandTypeRegistry implements ICommandTypeRegistry {
  
  private final String name;
  private final Map<String, List<ICommandType>> typesById = new ConcurrentHashMap<>();
//  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  
  CommandTypeRegistry(String instanceName) {
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
  public void addCommandType(ICommandType commandType) {
    Asserts.notNull(commandType.getRegistrationKey(),
        "ICommandType should be registered with non null registration " + "key: " + commandType);
    List<ICommandType> typeList = typesById.computeIfAbsent(commandType.getCommandTypeId(), id -> new ArrayList<>());
    typeList.add(commandType);
    Collections.sort(typeList);
  }
  
  
  @Override
  public ICommandType getPrent(ICommandType commandType) {
    String commandTypeId = commandType.getCommandTypeId();
    int i = commandTypeId.lastIndexOf(".");
    String parentCommandTypeId = commandTypeId.substring(0, i);
    if (typesById.containsKey(parentCommandTypeId)) {
      return typesById.get(parentCommandTypeId).get(0);
    }
    
    return null;
  }
  
  @Override
  public List<ICommandType> getChildren(ICommandType commandType) {
    String commandTypeId = commandType.getCommandTypeId();
    int length = commandTypeId.length();
    
    List<ICommandType> children = new ArrayList<>();
    
    List<String> childIds = typesById.keySet()
        .stream()
        .filter(id -> id.startsWith(commandType.getCommandTypeId() + "."))
        .map(subId -> {
          int i = subId.indexOf(".", length);
          return subId.substring(0, i);
        })
        .distinct()
        .sorted()
        .toList();
    
    childIds.forEach(childId -> {
      if (typesById.containsKey(childId)) {
        children.add(typesById.get(childId).get(0));
      }
    });
    
    return children;
  }

//  @Override
//  public CommandLineConfig getDefaultCommandLineConfig() {
//    List<CommandLineConfig> allRootCommandLineConfigs = getAllTopCommandLineConfigs(true);
//    CommandLineConfig rootCommandConfig = new CommandLineConfig(null, DEFAULT_ROOT_COMMAND_PATH, false, false);
//    rootCommandConfig.getSubCommands().addAll(allRootCommandLineConfigs);
//    return resolveCommandLineConfig(rootCommandConfig);
//  }

//  @Override
//  public CommandLineConfig resolveCommandLineConfig(CommandLineConfig commandLineConfig) {
//
//    String path = commandLineConfig.getPath();
//    List<ICommandType> commandTypes = getCommandTypesByPath().get(path);
//    CommandLine commandLine = null;
//
//    if (commandTypes != null) {
//      ICommandType commandType = commandTypes.get(0);
//      commandLine = new CommandLine(commandType.getCommandClass(), commandType.getCommandFactory());
//      commandLineConfig.setCommandType(commandType);
//      commandLineConfig.setCommandLine(commandLine);
//      if (commandLineConfig.isAddRecursive()) {
//        addCommandLineRecursive(commandType, commandLine);
//      }
//    } else {
//      //TODO
//    }
//
//    for (CommandLineConfig childConfig : commandLineConfig.getSubCommands()) {
//      resolveCommandLineConfig(childConfig);
//      String newName = childConfig.getName();
//      newName = newName != null ? newName : childConfig.getCommandType().getName();
//      commandLine.addSubcommand(newName, childConfig.getCommandLine());
//    }
//    return commandLineConfig;
//  }

//  private void addCommandLineRecursive(ICommandType commandType, CommandLine commandLine) {
//    commandType.internal()
//        .getDirectChildPaths()
//        .stream()
//        .filter(path -> path.contains(path))
//        .map(path -> typesById.get(path).get(0))
//        .forEach(ct -> {
//          CommandLine childCommandLine = new CommandLine(ct.getCommandClass(), ct.getCommandFactory());
//          commandLine.addSubcommand(ct.getName(), childCommandLine);
//          addCommandLineRecursive(ct, childCommandLine);
//        });
//  }
  
  @Override
  public Map<String, List<ICommandType>> getCommandTypes() {
    return typesById;
  }

//  @Override
//  public Map<Object, Object> getContext() {
//    return context;
//  }

//  private List<CommandLineConfig> getAllTopCommandLineConfigs(boolean setConfigRecursive) {
//    List<CommandLineConfig> topCommands = typesById.entrySet()
//        .stream()
//        .filter(entry -> !entry.getKey().startsWith("_") && entry.getValue().get(0).internal().isTopCommandType())
//        .map(entry -> new CommandLineConfig(null, entry.getValue().get(0).getPath(), setConfigRecursive, false))
//        .collect(Collectors.toList());
//
//    return topCommands;
//  }
}
