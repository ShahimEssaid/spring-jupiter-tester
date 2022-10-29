package com.essaid.picocli.commands.execute;

public interface IExecution {
  
  boolean isExecuted();
  
  Object getResult();
  
  Object execute();
  
  int getExitCode();
  
  boolean isExitGenerator();
}
