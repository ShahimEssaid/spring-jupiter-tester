package com.essaid.picocli.commands;

public abstract class Command implements ICommand.IICommand {
  
  
  @Override
  public IICommand internal() {
    return this;
  }
}
