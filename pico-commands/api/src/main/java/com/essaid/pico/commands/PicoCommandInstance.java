package com.essaid.pico.commands;

import picocli.CommandLine;

import java.util.Map;

public interface PicoCommandInstance {
  
  PicoCommandType getCommand();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
  
}
