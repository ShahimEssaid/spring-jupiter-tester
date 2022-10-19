package com.essaid.picocli.commands2;

import java.util.ArrayList;
import java.util.List;

public class CommandLineConfig {
  private final String name;
  private final String path;
  private final boolean recursive;
  private final List<CommandLineConfig> subCommands = new ArrayList<>();
  private ICommandType2 commandType;
  
  public ICommandType2 getCommandType() {
    return commandType;
  }
  
  public void setCommandType(ICommandType2 commandType) {
    this.commandType = commandType;
  }
  
  public CommandLineConfig(String name, String path, boolean recursive) {
    this.name = name;
    this.path = path;
    this.recursive = recursive;
  }
  
  public String getName() {
    return name;
  }
  
  public String getPath() {
    return path;
  }
  
  public boolean isRecursive() {
    return recursive;
  }
  
  public List<CommandLineConfig> getSubCommands() {
    return subCommands;
  }
}
