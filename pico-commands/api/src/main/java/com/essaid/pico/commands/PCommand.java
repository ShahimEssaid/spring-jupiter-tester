package com.essaid.pico.commands;

import picocli.CommandLine;

import java.util.Map;

public interface PCommand {
  
  PCommandType getType();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
}
