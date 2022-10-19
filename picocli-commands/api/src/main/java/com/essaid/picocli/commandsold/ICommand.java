package com.essaid.picocli.commandsold;

import java.util.concurrent.Callable;

public interface ICommand extends Callable<Integer> {
  
  IICommand internal();
  
  interface IICommand extends ICommand{
  
  }
}
