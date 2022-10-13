package com.essaid.picocli.commands;

import com.essaid.picocli.commands.impl.DefaultPCommandFactory;
import com.essaid.picocli.commands.impl.PCommandsImpl;

import java.util.Map;

public interface PCommands {
  
  String PICO_COMMAND_PREFIX = "picoCommands";
  String PICO_COMMAND_OUTPUT = PICO_COMMAND_PREFIX + ".output";
  
  static PCommands loadCommands() {
    return loadCommands(ClassLoader.getSystemClassLoader(), new DefaultPCommandFactory());
  }
  
  static PCommands loadCommands(ClassLoader classLoader) {
    return loadCommands(classLoader, new DefaultPCommandFactory());
  }
  
  static PCommands loadCommands(ClassLoader classLoader, PCommandFactory commandFactory) {
    PCommandsImpl commands = new PCommandsImpl(classLoader, commandFactory);
    commands.init();
    return commands;
  }
  
  ClassLoader getClassLoader();
  
  PCommandType getCommandTypeByPath(String path);
  
  Map<String, PCommandType> getCommandTypesByPath();
  PCommandType getCommandTypeByName(String name);
  
  Map<String, PCommandType> getCommandTypesByName();
  
  PCommandFactory getCommandFactory();
}
