package com.essaid.picocli.commands.tmp;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class PicoTestWhenObjectIsCreated {
  
  
  public static void main(String[] args) {
    
    CommandLine a = new CommandLine(com.essaid.picocli.commands.tmp.PicoTestWhenObjectIsCreated.A.class);
    

    CommandLine b = new CommandLine(com.essaid.picocli.commands.tmp.PicoTestWhenObjectIsCreated.B.class);
    CommandLine c = new CommandLine(com.essaid.picocli.commands.tmp.PicoTestWhenObjectIsCreated.C.class);
    
    a.addSubcommand(b);
    a.addSubcommand(c);
    a.addSubcommand("E", b);
    
//    CommandLine.ParseResult parseResult = a.parseArgs(new String[]{"A", "B"});
//    a.getExecutionStrategy().execute(parseResult);
    a.setExecutionStrategy(new CommandLine.RunFirst());
    a.execute(new String[]{ "B"});
  }
  
  @CommandLine.Command(name = "A", subcommandsRepeatable = true)
  static class A implements Callable<Integer> {
    
    A() {
      System.out.println("A constructed");
    }
    
    @Override
    public Integer call() throws Exception {
      System.out.println("Calling A");
      return 0;
    }
  }
  
  @CommandLine.Command(name = "B")
  static class B implements Callable<Integer> {
    
    B() {
      System.out.println("B constructed");
    }
    
    @Override
    public Integer call() throws Exception {
      System.out.println("Calling B");
      return 0;
    }
  }
  
  @CommandLine.Command(name = "C")
  static class C implements Callable<Integer> {
    C() {
      System.out.println("C constructed");
    }
    
    @Override
    public Integer call() throws Exception {
      System.out.println("Calling C");
      return 0;
    }
  }
}
