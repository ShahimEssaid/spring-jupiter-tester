package com.essaid.pcli.command;

import com.essaid.pcli.type.ICommandType;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer>, Runnable {
  
  ICommandType getCommandType();
  void setCommandType(ICommandType commandType);
  
}
