package com.essaid.pico.commands;

import com.essaid.pico.commands.internal.PCommandInternal;
import picocli.CommandLine;

import java.util.Map;

public interface PCommand {
  
  PCommandType getPCommandType();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
  
  PCommandInternal internal();
}
