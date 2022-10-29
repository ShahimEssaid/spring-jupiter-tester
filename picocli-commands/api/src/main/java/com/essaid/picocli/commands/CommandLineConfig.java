package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CommandLineConfig {
  
  private static Predicate<CommandLineConfig> RECURSIVELY_ADDED_FILTER =
      commandLineConfig -> commandLineConfig.recursivelyAdded;
  
  private final String name;
  private final String path;
  private final boolean addRecursive;
  private final boolean recursivelyAdded;
  private final List<CommandLineConfig> subCommands = new ArrayList<>();
  private ICommandType commandType;
  private CommandLine commandLine;
  
  public CommandLineConfig(String name, String path, boolean addRecursive, boolean recursivelyAdded) {
    this.name = name;
    this.path = path;
    this.addRecursive = addRecursive;
    this.recursivelyAdded = recursivelyAdded;
  }
  
  public CommandLine getCommandLine() {
    return commandLine;
  }
  
  public void setCommandLine(CommandLine commandLine) {
    this.commandLine = commandLine;
  }
  
  /**
   * Clears this config recursively as if it wasn't resolved against a {@link ICommands} instance so that it can be
   * re-resolved again.  It removes all the {@link CommandLineConfig#recursivelyAdded} instances and clears
   * {@link CommandLineConfig#commandType} and {@link CommandLineConfig#commandLine};
   */
  public void hardReset() {
    setCommandType(null);
    setCommandLine(null);
    getSubCommands().forEach(commandLineConfig -> commandLineConfig.hardReset());
    getSubCommands().removeIf(RECURSIVELY_ADDED_FILTER);
  }
  
  public ICommandType getCommandType() {
    return commandType;
  }
  
  public void setCommandType(ICommandType commandType) {
    this.commandType = commandType;
  }
  
  public String getName() {
    return name;
  }
  
  public String getPath() {
    return path;
  }
  
  public boolean isAddRecursive() {
    return addRecursive;
  }
  
  public List<CommandLineConfig> getSubCommands() {
    return subCommands;
  }
}
