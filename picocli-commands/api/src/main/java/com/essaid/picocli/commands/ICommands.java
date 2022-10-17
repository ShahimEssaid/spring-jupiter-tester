package com.essaid.picocli.commands;

import com.essaid.picocli.commands.impl.CommandLine;

public interface ICommands {
  
  CommandLine getCommandLine();
  
  CommandLine getCommandLine(String rootCommandPath, String noOpCommandPath, String ...subCommandPaths);
  
  IICommands internal();
  
  interface  IICommands extends ICommands {
  
  }
}

