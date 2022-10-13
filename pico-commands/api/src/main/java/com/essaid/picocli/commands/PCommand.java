package com.essaid.picocli.commands;

import com.essaid.picocli.commands.internal.PCommandInternal;
import picocli.CommandLine;

import java.util.Map;

public interface PCommand {
  
  PCommandType getPCommandType();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
  
  PCommandInternal internal();
}
