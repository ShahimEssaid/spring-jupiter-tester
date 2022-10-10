package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PicoCommandType;
import com.essaid.pico.commands.PicoCommands;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;


public class PicoCommandsImpl implements PicoCommands {
  
  private final ClassLoader classLoader;
  private final Map<String, PicoCommandType> commands = new HashMap<>();
  private final CommandLine.IFactory picoFactory;
  
  public PicoCommandsImpl(ClassLoader classLoader, CommandLine.IFactory picoFactory) {
    this.classLoader = classLoader;
    this.picoFactory = picoFactory;
  }
  
  @Override
  public ClassLoader getClassLoader() {
    return classLoader;
  }
  
  @Override
  public PicoCommandType getCommandByPath(String path) {
    return commands.get(path);
  }
  
  @Override
  public PicoCommandType getNoOpCommand() {
    return commands.get(NoOpCommandType.NO_OP_COMMAND_NAME);
  }
  
  
  public void init() {
    ServiceLoader<PicoCommandType> loader = ServiceLoader.load(PicoCommandType.class, classLoader);
    loader.stream().forEach(commandProvider -> {
      
      PicoCommandType commandType = commandProvider.get();
      Arrays.stream(commandType.getPaths()).forEach(path -> {
        PicoCommandType existingCommandType = commands.putIfAbsent(path, commandType);
        
        if (existingCommandType != null) {
          throw new IllegalStateException("Command type:" + commandType + " adds path " + path + " but path already " + "taken by command type: " + existingCommandType + path);
        }
      });
    });
    
  }
  
  @Override
  public CommandLine.IFactory getPicoFactory() {
    return picoFactory;
  }
}
