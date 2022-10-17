package com.essaid.picocli.commands;

import java.util.Properties;

public interface ICommands {
  
  IICommands internal();
  
  interface  IICommands extends ICommands {
  
  }
}

