package com.essaid.picocli.commands;

import com.essaid.picocli.commands.impl.CommandLine;

import java.util.concurrent.Callable;

public interface ICommands {
  
  CommandLine getCommandLine();
  
  CommandLine getCommandLine( String ...subCommandPaths);
  
  IICommands internal();
  
  interface  IICommands extends ICommands {
  
  }
}

