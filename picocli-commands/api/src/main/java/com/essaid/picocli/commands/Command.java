package com.essaid.picocli.commands;

import picocli.CommandLine;

public class Command implements ICommand {
  
  private ICommandType2 commandType;
  
  @Override
  public ICommandType2 getCommandType() {
    return commandType;
  }
  
  @Override
  public void setCommandType(ICommandType2 commandType) {
    this.commandType = commandType;
  }
  
  @Override
  public Integer call() throws Exception {
    return null;
  }
}


@CommandLine.Command(name = ICommands2.DEFAULT_ROOT_COMMAND_PATH, hidden = true, subcommandsRepeatable = true)
class DefaultRootCommand extends Command{


}