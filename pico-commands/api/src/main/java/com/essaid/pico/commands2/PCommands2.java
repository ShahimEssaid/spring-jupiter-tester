package com.essaid.pico.commands2;

import com.essaid.pico.commands.PCommandFactory;
import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.PCommands;
import com.essaid.pico.commands2.exception.CommandsException;
import com.essaid.pico.commands2.impl.DefaultFactory;
import com.essaid.pico.commands2.impl.PCommands2Impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface PCommands2 {
  
  enum Environment {
    PLAIN() {
      @Override
      public PCommandFactory2 getCommandFactory(PCommandType2 commandType2, Map<Object, Object> context,
                                                Object... args) {
        return new DefaultFactory(commandType2, context, args);
      }
    }, SPRING() {
      @Override
      public PCommandFactory2 getCommandFactory(PCommandType2 commandType2,Map<Object, Object> context, Object... args) {
        return null;
      }
    };
    
    private final Map<Object, Object> context = new ConcurrentHashMap<>();
    
    Environment() {
    }
    
    abstract public PCommandFactory2 getCommandFactory(PCommandType2 commandType2, Map<Object, Object> context,
                                                       Object... args);
    
    public Map<Object, Object> getContext() {
      return context;
    }
  }
  
  String PICO_COMMAND_PREFIX = "picoCommands";
  String PICO_COMMAND_OUTPUT = PICO_COMMAND_PREFIX + ".output";
  
  static PCommands2 loadCommands() {
    return loadCommands(ClassLoader.getSystemClassLoader());
  }
  
  static PCommands2 loadCommands(ClassLoader classLoader) {
    return loadCommands(Environment.PLAIN, classLoader);
  }
  
  static PCommands2 loadCommands(Environment environment, ClassLoader classLoader) {
    PCommands2Impl commands = new PCommands2Impl(environment, classLoader);
    //TODO: see if we need
    List<CommandsException> commandsExceptions = commands.loadCommandTypes();
    return commands;
  }
  
  ClassLoader getClassLoader();
  
  Environment getEnvironment();
  
  PCommandType2 getCommandTypeByPath(String path);
  
  Map<String, PCommandType2> getCommandTypesByPath();
  
  PCommandType2 getCommandTypeByName(String name);
  
  Map<String, PCommandType2> getCommandTypesByName();
  
  PCommandFactory2 getCommandFactory();
}
