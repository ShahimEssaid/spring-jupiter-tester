package com.essaid.picocli.commands.command;

import com.essaid.picocli.commands.type.ICommandType;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer> {
  
  ICommandType getCommandType();
  void setCommandType(ICommandType commandType);
  
}
