package com.essaid.picocli.commands;

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


@CommandLine.Command(name = ICommands.DEFAULT_ROOT_COMMAND_PATH, hidden = true, subcommandsRepeatable = true)
class DefaultRootCommand extends Command{


}