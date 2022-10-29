package com.essaid.picocli.commands.command;

import com.essaid.picocli.commands.ICommands;
import com.essaid.picocli.commands.type.ICommandType;
import picocli.CommandLine;

public class Command implements ICommand {
  
  private ICommandType commandType;
  
  @Override
  public ICommandType getCommandType() {
    return commandType;
  }
  
  @Override
  public void setCommandType(ICommandType commandType) {
    this.commandType = commandType;
  }
  
  @Override
  public Integer call() throws Exception {
    return null;
  }
}
