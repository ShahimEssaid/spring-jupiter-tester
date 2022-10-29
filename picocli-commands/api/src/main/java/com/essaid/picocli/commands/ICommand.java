package com.essaid.picocli.commands;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer> {
  
  ICommandType getCommandType();
  void setCommandType(ICommandType commandType);
  
}
