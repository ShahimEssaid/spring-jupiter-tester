package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandManager;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class CommandManager implements ICommandManager.IICommandManager {
  
  private static ICommandManager defaultManager;
  
  private final ClassLoader classLoader;
  private final List<Object> context;
  private final Environment environment;
  
  protected CommandManager(ClassLoader classLoader, List<Object> context, Environment environment) {
    this.classLoader = classLoader;
    this.context = context;
    this.environment= environment;
  }
  
  public static ICommandManager getDefaultManager() {
    if (Objects.isNull(defaultManager)) {
      defaultManager = ICommandManager.createCommandManager(ClassUtils.getDefaultClassLoader(), new ArrayList<>(),
          Environment.CLASSPATH);
    }
    
    return defaultManager;
  }
  
  public static ICommandManager createManager(ClassLoader classLoader, List<Object> context, Environment environment) {
    CommandsAsserts.notNull(classLoader, "Classloader can't be null when creating ICommandManager.");
    CommandsAsserts.notNull(context, "Context can't be null when creating ICommandManager.");
    CommandsAsserts.notNull(environment, "Environment can't be null when creating ICommandManager.");
    
    return switch (environment) {
      case CLASSPATH -> new ClasspathManager(classLoader, context, environment);
      case SPRING -> new SpringManager(classLoader, context, environment);
    };
    
  }
  
  @Override
  public IICommandManager internal() {
    return this;
  }
  
  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }
}
