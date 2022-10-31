package com.essaid.pcli.execute;

public interface ICommandExecution {
  
  boolean isExecuted();
  
  Object getResult();
  
  Object execute();
  
  int getExitCode();
  
  boolean isExitGenerator();
}
