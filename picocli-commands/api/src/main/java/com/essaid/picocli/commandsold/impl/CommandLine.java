package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commandsold.ICommandLine;
import com.essaid.picocli.commandsold.ICommandType;

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
