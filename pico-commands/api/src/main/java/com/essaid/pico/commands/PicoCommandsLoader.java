package com.essaid.pico.commands;

import com.essaid.pico.commands.impl.PicoCommandsImpl;
import picocli.CommandLine;

import java.util.ServiceLoader;

public class PicoCommandsLoader {
  
  private PicoCommandsLoader() {
  }
  
  public static PicoCommands loadCommands(ClassLoader classLoader) {
    return loadCommands(classLoader, CommandLine.defaultFactory());
  }
  
  public static PicoCommands loadCommands(ClassLoader classLoader, CommandLine.IFactory picoFactory) {
    PicoCommands commands = new PicoCommandsImpl(classLoader, picoFactory);
    return commands;
  }
  
  
}
