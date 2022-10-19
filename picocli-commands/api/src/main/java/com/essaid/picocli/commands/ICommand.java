package com.essaid.picocli.commands;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer> {
  
  ICommandType2 getCommandType();
  void setCommandType(ICommandType2 commandType);
  
}
