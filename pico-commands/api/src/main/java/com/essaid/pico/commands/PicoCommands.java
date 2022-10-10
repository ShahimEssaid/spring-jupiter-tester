package com.essaid.pico.commands;

import picocli.CommandLine;

public interface PicoCommands {
  
  ClassLoader getClassLoader();
  
  PicoCommandType getCommandByPath(String path);
  
  PicoCommandType getNoOpCommand();
  
  CommandLine.IFactory getPicoFactory();
}
