package com.essaid.picocli.commandsold;

public abstract class Command implements ICommand.IICommand {
  
  
  @Override
  public IICommand internal() {
    return this;
  }
}
