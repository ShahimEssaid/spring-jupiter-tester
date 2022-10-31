package com.essaid.pcli.tmp;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class check_if_initial_user_object_is_kept {
  
  public static void main(String[] args) {
    CommandLine commandLine = new CommandLine(A.class);
    CommandLine.ParseResult parseResult = commandLine.parseArgs(new String[]{});
    commandLine.getExecutionStrategy().execute(parseResult);
  

  }
  
  
  @CommandLine.Command(name = "a")
  static class A implements Callable {
    
    private final String name;
    
    A(String name) {
      this.name = name;
      System.out.println("Name constructor: " + name);
    }
    
    A() {

      name = "default";
      System.out.println("Default constructor: " + name);
    }
    
    @Override
    public Object call() throws Exception {
      System.out.println("Name is: " + name);
      return null;
    }
  }
  
}
