package com.essaid.picocli;

import picocli.CommandLine;

import java.lang.annotation.Annotation;
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
     * Check if the {@link IEnvironment} already has a {@link ICommandType} with the same name. If you need to add
     * this command type, you can manually add it with a new name through
     * {@link IIEnvironment#addCommandType(String, String, String[], Class)}.
     *
     * @param commandType The command name to check for.
     * @return if command with same name already exists.
     */
    boolean containsCommandType(ICommandType commandType);
    
    /**
     * @param commandName The command name to check for.
     * @return true if there is already a command with that name.
     */
    boolean containsCommandName(String commandName);
    
    /**
     * Creates and adds a new {@link ICommandType} if there isn't one with that name already.
     *
     * @param name The command name. Is final and must be unique in the {@link IEnvironment}
     * @param description A mutable description until {@link ICommands} instantiated from this environment.
     * @param paths A mutable list of paths to form the "tree" of commands in the {@link ICommands} instance.
     * @param commandClass The class that has a @{@link picocli.CommandLine.Command} annotation. This is final.
     * @return true if it was successfully added. false should mean there is already a command with that name in the
     * environment.
     */
    boolean addCommandType(String name, String description, String[] paths, Class<?> commandClass);
    
    List<ICommandType> findServiceLoaderCommandTypes();
    
    List<ICommandType> findClasspathScannerCommandTypes(Class<? extends Annotation> annotationType);
    
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
  public Map<String, Object> getProperties() {
    return null;
  }
  
  @Override
  public ICommands getCommands() {
    return null;
  }
  
  @Override
  public boolean containsCommandType(ICommands.ICommandType commandType) {
    return false;
  }
  
  @Override
  public boolean containsCommandName(String commandName) {
    return false;
  }
  
  @Override
  public boolean addCommandType(String name, String description, String[] paths, Class<?> commandClass) {
    return false;
  }
  
  @Override
  public List<ICommands.ICommandType> findServiceLoaderCommandTypes() {
    return null;
  }
  
  @Override
  public List<ICommands.ICommandType> findClasspathScannerCommandTypes(Class<? extends Annotation> annotationType) {
    return null;
  }
  
  
  @Override
  public List<ICommands.ICommandType> findSpringCommandTypes() {
    return null;
  }
  
  @Override
  public IIEnvironment internal() {
    return this;
  }
  
  @Override
  public ICommands.IEnvironment copy() {
    return null;
  }
  
  @Override
  public void lock() {
  
  }
  
  @Override
  public boolean isLocked() {
    return false;
  }
}

class Commands implements ICommands {
  
  @Override
  public IEnvironment getDefaultEnvironment() {
    return null;
  }
  
  @Override
  public IEnvironment createNewEnvironment() {
    return null;
  }
}

class Util {
  static boolean isWithoutSubCommands(Class<?> commandClass) {
    return new CommandLine(commandClass).getSubcommands().isEmpty();
  }
}
