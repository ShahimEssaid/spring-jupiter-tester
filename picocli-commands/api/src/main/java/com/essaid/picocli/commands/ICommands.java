package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ICommands {
  
  String DEFAULT_ICOMMANDS_INSTANCE_NAME = "_default";
  String DEFAULT_ROOT_COMMAND_PATH = "_default-root-command";
  
  static ICommands getDefaultInstance() {
    return getOrCreateInstance(DEFAULT_ICOMMANDS_INSTANCE_NAME);
  }
  
  static ICommands getInstance(String instanceName) {
    return IICommands.INSTANCES.get(instanceName);
  }
  
  static ICommands getOrCreateInstance(String instanceName) {
    return IICommands.INSTANCES.computeIfAbsent(instanceName, s -> new Commands(s));
  }
  
  static ICommands removeInstance(String instanceName) {
    return IICommands.INSTANCES.remove(instanceName);
  }
  
  String getName();
  
  ICommandType addCommandType(String name, String path, Class<? > commandClass, CommandLine.IFactory factory,
                              String info);
  
  void removeCommandType(ICommandType commandType);
  
  
  /**
   * This will add all the {@link ICommandType} that do not have a "parent" command in the paths.
   *
   * @return
   */
  CommandLineConfig getDefaultCommandLineConfig();
  
  public CommandLineConfig resolveCommandLineConfig(CommandLineConfig commandLineConfig);
  
  
  IICommands internal();
  
  interface IICommands extends ICommands {
    static String DEFAULT_INSTANCE_NAME = "_default";
    
    static Map<String, IICommands> INSTANCES = new ConcurrentHashMap<>();
    List<ICommandType> typesList = new ArrayList<>();
    
    Map<String, List<ICommandType>> getCommandTypesByPath();
    
    Map<Object, Object> getContext();
  }
}

