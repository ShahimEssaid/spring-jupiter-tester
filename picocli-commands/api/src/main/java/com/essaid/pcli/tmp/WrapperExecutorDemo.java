package com.essaid.pcli.tmp;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class WrapperExecutorDemo {
  
  public static void main(String[] args) {
    CommandLine a = new CommandLine(A.class);
    a.setExecutionStrategy(new WrapperExecutor(new CommandLine.RunAll()));
    
    CommandLine b = new CommandLine(B.class);
    b.setExecutionStrategy(new WrapperExecutor2(new CommandLine.RunLast()));
    
    a.addSubcommand("z", b);
    
    
    CommandLine.ParseResult parseResult = a.parseArgs(new String[]{"z", "z"});
    a.execute(new String[]{"z", "z"});
    
  }
  
  
  @CommandLine.Command(name = "A", subcommandsRepeatable = true)
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
  
  public static class WrapperExecutor2 extends WrapperExecutor {
    
    public WrapperExecutor2(CommandLine.IExecutionStrategy executionStrategy) {
      super(executionStrategy);
    }
  }
  
}
