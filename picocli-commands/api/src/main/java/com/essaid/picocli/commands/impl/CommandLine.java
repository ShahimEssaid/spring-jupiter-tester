package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandLine;
import com.essaid.picocli.commands.ICommandType;

public class CommandLine extends picocli.CommandLine implements ICommandLine {
  
  private final ICommandType commandType;
  
  CommandLine(ICommandType commandType){
    super(commandType.getCommandClass(), commandType.getFactory());
    this.commandType = commandType;
  }
  
  ICommandType getCommandType(){
    return commandType;
  }
}
