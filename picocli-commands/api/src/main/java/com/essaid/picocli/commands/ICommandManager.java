package com.essaid.picocli.commands;

import com.essaid.picocli.commands.impl.CommandManager;

import java.lang.annotation.Annotation;
import java.util.List;

public interface ICommandManager {
  static ICommandManager getDefaultManager(){
    return CommandManager.getDefaultManager();
  }
  
  enum Environment{
    CLASSPATH, SPRING;
  }
  
  static ICommandManager createCommandManager(ClassLoader classLoader, List<Object> context, Environment environment){
    return CommandManager.createManager(classLoader, context, environment);
  }
  
//  /**
//   * Check if the {@link ICommandManager} already has a {@link ICommandType} with the same name. If you need
//   * to add
//   * this command type, you can manually add it with a new name through
//   * {@link IICommandManager#addCommandType(String, String, String[], Class)}.
//   *
//   * @param commandType The command name to check for.
//   * @return if command with same name already exists.
//   */
//  boolean containsCommandType(ICommandType commandType);
  
  /**
   * @param path The command name to check for.
   * @return true if there is already a command with that name.
   */
  boolean containsCommandPath(String path);
  
  /**
   * Creates and adds a new {@link ICommandType} if there isn't one with that name already.
   *
   * @param name         The command name. Is final and must be unique in the {@link ICommandManager}
   * @param description  A mutable description until {@link ICommands} instantiated from this environment.
   * @param paths        A mutable list of paths to form the "tree" of commands in the {@link ICommands} instance.
   * @param commandClass The class that has a @{@link picocli.CommandLine.Command} annotation. This is final.
   * @return true if it was successfully added. false should mean there is already a command with that name in the
   * environment.
   */
  boolean addCommandType(String name, String description, String[] paths, Class<?> commandClass);
  
  List<ICommandType> findServiceLoaderCommandTypes();
  
  List<ICommandType> findClasspathScannerCommandTypes(Class<? extends Annotation> annotationType);
  
  List<ICommandType> findSpringCommandTypes();
  
  List<ICommandType> getCommandTypes();
  
  
  ICommands getCommands();
  
  
  ClassLoader getClassLoader();
  
  IICommandManager internal();
  
  interface IICommandManager extends ICommandManager {
  }
}
