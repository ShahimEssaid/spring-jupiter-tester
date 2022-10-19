package com.essaid.picocli.commands2;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer> {
  
  ICommandType2 getCommandType();
  void setCommandType(ICommandType2 commandType);
  
}
