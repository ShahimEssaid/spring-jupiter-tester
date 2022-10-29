package com.essaid.picocli.commands;

import com.essaid.picocli.commands.command.CommandLineConfig;
import com.essaid.picocli.commands.type.ICommandType;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface ICommands {
  
  String DEFAULT_ICOMMANDS_NAME = "_default";
  String DEFAULT_ROOT_COMMAND_PATH = "_default-root-command";
  
  static ICommands getDefaultCommandsInstance() {
    return getOrCreateCommandsInstance(DEFAULT_ICOMMANDS_NAME);
  }
  
  static ICommands getCommandsInstance(String instanceName) {
    return IICommands.INSTANCES.get(instanceName);
  }
  
  static ICommands getOrCreateCommandsInstance(String instanceName) {
    return IICommands.INSTANCES.computeIfAbsent(instanceName, s -> new Commands(s));
  }
  
  static ICommands removeCommandsInstance(String instanceName) {
    return IICommands.INSTANCES.remove(instanceName);
  }
  
  String getName();
  
  ICommandType addCommandType(ICommands commands, String name, String path, int order, String title,
                              String shortDescription, String longDescription, String notes,
                              Class<?> commandClass, CommandLine.IFactory factory,
                              CommandLine.IExecutionStrategy strategy) ;
  
  
  
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
    
    Map<String, List<ICommandType>> getCommandTypesByPath();
    
    Map<Object, Object> getContext();
  }
}

