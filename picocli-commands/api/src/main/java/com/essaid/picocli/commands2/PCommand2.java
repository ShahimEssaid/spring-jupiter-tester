package com.essaid.picocli.commands2;

import com.essaid.picocli.commands.PCommandType;
import com.essaid.picocli.commands.internal.PCommandInternal;
import picocli.CommandLine;

import java.util.Map;

public interface PCommand2 {
  
  PCommandType getPCommandType();
  
  CommandLine getCommandLine();
  
  Map<Object, Object> getContext();
  
  PCommandInternal internal();
}
