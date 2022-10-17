package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandType;
import com.essaid.picocli.commands.ICommands;

import java.lang.annotation.Annotation;
import java.util.List;

public class SpringManager extends CommandManager{
  protected SpringManager(ClassLoader classLoader, List<Object> context, Environment environment) {
    super(classLoader, context, environment);
  }
  
  
  @Override
  public boolean containsCommandPath(String commandName) {
    return false;
  }
  
  @Override
  public boolean addCommandType(String name, String description, String[] paths, Class<?> commandClass) {
    return false;
  }
  
  @Override
  public List<ICommandType> findServiceLoaderCommandTypes() {
    return null;
  }
  
  @Override
  public List<ICommandType> findClasspathScannerCommandTypes(Class<? extends Annotation> annotationType) {
    return null;
  }
  
  @Override
  public List<ICommandType> findSpringCommandTypes() {
    return null;
  }
  
  @Override
  public List<ICommandType> getCommandTypes() {
    return null;
  }
  
  @Override
  public ICommands getCommands() {
    return null;
  }
}
