package com.essaid.pico.commands2;

import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.internal.PCommandInternal;
import picocli.CommandLine;

import java.util.Map;

public interface PCommand2 {
  
  PCommandType getPCommandType();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
  
  PCommandInternal internal();
}
