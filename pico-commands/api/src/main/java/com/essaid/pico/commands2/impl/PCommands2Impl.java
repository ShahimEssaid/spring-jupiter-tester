package com.essaid.pico.commands2.impl;

import com.essaid.pico.commands.PCommandFactory;
import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands2.PCommandFactory2;
import com.essaid.pico.commands2.PCommandType2;
import com.essaid.pico.commands2.PCommands2;
import com.essaid.pico.commands2.exception.CommandsException;
import com.essaid.pico.commands2.exception.ExistingCommandTypeName;
import com.essaid.pico.commands2.exception.ExistingCommandTypePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class PCommands2Impl implements PCommands2 {
  
  private static Logger logger = LoggerFactory.getLogger(PCommands2Impl.class);
  
  private final Environment environment;
  private final ClassLoader classLoader;
  private ServiceLoader<PCommandType2> serviceLoader;
  private Map<String, PCommandType2> commandTypeByName = new ConcurrentHashMap<>();
  private Map<String, PCommandType2> commandTypeByPath = new ConcurrentHashMap<>();
  
  
  public PCommands2Impl(Environment environment, ClassLoader classLoader) {
    this.environment = environment;
    this.classLoader = classLoader;
  }
  
  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  @Override
  public Environment getEnvironment() {
    return environment;
  }
  
  @Override
  public PCommandType2 getCommandTypeByPath(String path) {
    return commandTypeByPath.get(path);
  }
  
  @Override
  public Map<String, PCommandType2> getCommandTypesByPath() {
    return commandTypeByPath;
  }
  
  @Override
  public PCommandType2 getCommandTypeByName(String name) {
    return commandTypeByName.get(name);
  }
  
  @Override
  public Map<String, PCommandType2> getCommandTypesByName() {
    return commandTypeByName;
  }
  
  @Override
  public PCommandFactory2 getCommandFactory() {
    return null;
  }
  
  public List<CommandsException> loadCommandTypes() {
    List<CommandsException> exceptions = new ArrayList<>();
    if (Objects.nonNull(serviceLoader)) {
      throw new IllegalStateException("Pico commands already loaded.");
    }
    
    this.serviceLoader = ServiceLoader.load(PCommandType2.class, classLoader);
    
    serviceLoader.stream().forEach(provider -> {
      PCommandType2 type = provider.get();
      type.internal().setCommands(this);
      String typeName = type.getName();
      
      PCommandType2 existingName = commandTypeByName.putIfAbsent(typeName, type);
      if (Objects.nonNull(existingName)) {
        String message =
            "Command type: " + type + " adds a " + "duplicate " + "name: " + typeName + " for existing " + "command " + "type: " + existingName + " while service loading command " + "types.";
        logger.warn(message);
        exceptions.add(new ExistingCommandTypeName(typeName, existingName, type, message));
      } else {
        logger.info("Loaded command name: " + typeName + " from command type: " + type);
      }
      
      String[] paths = type.getPaths();
      Arrays.stream(paths).forEach(path -> {
        PCommandType2 existingPath = commandTypeByPath.putIfAbsent(path, type);
        if (Objects.nonNull(existingPath)) {
          String message = "Command type: " + type + " adds a" + " " + "duplicate " + "path: " + path + " for " +
              "existing command type: " + existingPath + " while service " + "loading command " + "types.";
          logger.warn(message);
          exceptions.add(new ExistingCommandTypePath(typeName, existingName, type, message));
        } else {
          logger.info("Loaded command path: " + path + " from command type: " + type);
        }
      });
      
    });
    
    return exceptions;
  }
}
