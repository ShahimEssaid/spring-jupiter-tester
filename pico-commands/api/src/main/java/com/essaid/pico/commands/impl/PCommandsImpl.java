package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PCommandFactory;
import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.PCommands;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;


public class PCommandsImpl implements PCommands {
  
  private final ClassLoader classLoader;
  private final Map<String, PCommandType> commandsByPath = new HashMap<>();
  private final Map<String, PCommandType> commandsByName = new HashMap<>();
  private final PCommandFactory commandFactory;
  private ServiceLoader<PCommandType> loader;
  
  public PCommandsImpl(ClassLoader classLoader, PCommandFactory commandFactory) {
    this.classLoader = classLoader;
    this.commandFactory = commandFactory;
  }
  
  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  @Override
  public PCommandType getCommandTypeByPath(String path) {
    return commandsByPath.get(path);
  }
  
  @Override
  public Map<String, PCommandType> getCommandTypesByPath() {
    return commandsByPath;
  }
  
  @Override
  public PCommandType getCommandTypeByName(String name) {
    return commandsByName.get(name);
  }
  
  @Override
  public Map<String, PCommandType> getCommandTypesByName() {
    return commandsByName;
  }
  
  
  public void init() {
    if(Objects.nonNull(loader)){
      throw new IllegalStateException("Pico commands already loaded.");
    }
    this.loader = ServiceLoader.load(PCommandType.class, classLoader);
    loader.stream().forEach(commandProvider -> {
      
      PCommandType commandType = commandProvider.get();
      
      String name = commandType.getName();
      PCommandType existingName = commandsByName.put(name, commandType);
      if (Objects.nonNull(existingName)) {
        throw new IllegalStateException("Command type:" + commandType + " with name " + name + " but name already " + "taken by command type: " + existingName);
      }
      
      Arrays.stream(commandType.getPaths()).forEach(path -> {
        PCommandType existingPath = commandsByPath.putIfAbsent(path, commandType);
        
        if (existingPath != null) {
          throw new IllegalStateException("Command type:" + commandType + " adds path " + path + " but path already " + "taken by command type: " + existingPath);
        }
      });
    });
    
  }
  
  @Override
  public PCommandFactory getCommandFactory() {
    return commandFactory;
  }
}
