package com.essaid.picocli.commandsold;

import com.essaid.picocli.commandsold.impl.CommandLine;

public interface ICommands {
  
  CommandLine getCommandLine();
  
  CommandLine getCommandLine( String ...subCommandPaths);
  
  IICommands internal();
  
  interface  IICommands extends ICommands {
  
  }
}

