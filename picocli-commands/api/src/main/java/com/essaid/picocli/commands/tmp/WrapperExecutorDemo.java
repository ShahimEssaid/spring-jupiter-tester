package com.essaid.picocli.commands.tmp;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class WrapperExecutorDemo {
  
  public static void main(String[] args) {
    CommandLine cl = new CommandLine(A.class);
    cl.setExecutionStrategy(new WrapperExecutor(new CommandLine.RunAll()));
    cl.execute(new String[]{"B"});
    
  }
  
  
  @CommandLine.Command(name = "A", subcommands = B.class, subcommandsRepeatable = true)
  public static class A implements Callable<Integer> {
    
    public A() {
      System.out.println("Constructing: " + this);
    }
    
    @Override
    public Integer call() throws Exception {
      System.out.println("Calling: " + this);
      return null;
    }
  }
  
  @CommandLine.Command(name = "B")
  public static class B implements Callable<Integer> {
    
    public B() {
      System.out.println("Constructing: " + this);
    }
    
    @Override
    public Integer call() throws Exception {
      System.out.println("Calling: " + this);
      return null;
    }
  }
  
  public static class WrapperExecutor implements CommandLine.IExecutionStrategy {
    
    private final CommandLine.IExecutionStrategy executionStrategy;
    
    public WrapperExecutor(CommandLine.IExecutionStrategy executionStrategy) {
      this.executionStrategy = executionStrategy;
    }
    
    @Override
    public int execute(CommandLine.ParseResult parseResult) throws CommandLine.ExecutionException,
        CommandLine.ParameterException {
      System.out.println("Executing: " + parseResult.commandSpec().userObject());
      executionStrategy.execute(parseResult);
      return 0;
    }
  }
  
}
