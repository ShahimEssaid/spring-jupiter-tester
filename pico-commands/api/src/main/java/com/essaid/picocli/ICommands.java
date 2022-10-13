package com.essaid.picocli;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;

public interface ICommands {
  interface Constants {
    String COMMANDS_NAMESPACE = "com.essaid.picocli.commands";
    String COMMANDS_CLASSLOADER = COMMANDS_NAMESPACE + ".classloader";
  }
  
  IEnvironment getDefaultEnvironment();
  
  IEnvironment createNewEnvironment();
  
  @interface Command {
  
  }
  
  interface IEnvironment {
    /**
     * Check if the {@link IEnvironment} already has a {@link ICommandType} with the same name.
     *
     * @param commandType
     * @return
     */
    boolean containsCommandType(ICommandType commandType);
    
    /**
     * @param commandName
     * @return true if there is already a command with that name.
     */
    boolean containsCommandName(String commandName);
    
    /**
     * Creates and adds a new {@link ICommandType} if there isn't one with that name already.
     *
     * @param name
     * @param description
     * @param paths
     * @param commandClass
     * @return true if it was successfully added. false should mean there is already a command with that name in the
     * environment.
     */
    boolean addCommandType(String name, String description, String[] paths, Class<?> commandClass);
    
    List<ICommandType> findServiceLoaderCommandTypes();
    
    List<ICommandType> findClasspathScannerCommandTypes();
    
    List<ICommandType> findSpringCommandTypes();
    
    List<ICommandType> getCommandTypes();
    
    Map<String, Object> getProperties();
    
    ICommands getCommands();
    
    IIEnvironment internal();
  }
  
  interface ICommandType {
  
  }
  
  class CommandType implements ICommandType {
    private final String name;
    private final Class<?> commandClass;
    private List<String> paths;
    private String description;
    
    public CommandType(String name, String description, List<String> paths, Class<?> commandClass,
                       IEnvironment environment) {
      this.name = name;
      this.description = description;
      this.paths = paths;
      this.commandClass = commandClass;
    }
    
    
  }
  
  class CommandLine extends picocli.CommandLine {
    
    CommandLine(Object command) {
      super(command);
    }
    
    CommandLine(Object command, IFactory factory) {
      super(command, factory);
    }
  }
  
}

interface IIEnvironment extends ICommands.IEnvironment {
  ICommands.IEnvironment copy();
  
  void lock();
  
  boolean isLocked();
}

class Environment implements IIEnvironment {
  
  @Override
  public List<ICommands.ICommandType> getCommandTypes() {
    return null;
  }
  
  @Override
  public ICommands.ICommandType addCommandType(String name, String description, String[] paths, Class<?> commandClass) {
    return null;
  }
  
  @Override
  public IIEnvironment internal() {
    return this;
  }
  
}

class Commands implements ICommands {

}

class Util {
  static boolean noSubCommands(Class<?> commandClass) {
    return new CommandLine(commandClass).getSubcommands().isEmpty();
  }
}
