package com.essaid.picocli.commands.tmp;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PicocliDefaultFactoryDebugging {
  
  public static void main(String[] args) {
    
    CommandLine a = new CommandLine(CommandA.class);
    a.addSubcommand("b", CommandB.class);
    a.addSubcommand("c", CommandC.class);
    
    //a.execute(new String[]{"-abc"});
    
    
    CommandLine.ParseResult parseResult = a.parseArgs(new String[]{"-abc", "b", "b"});
    a.getExecutionStrategy().execute(parseResult);
    
    
  }
}


@CommandLine.Command(name = "command-a", subcommandsRepeatable = true)
class CommandA implements Callable<Integer> {
  
  CommandA() {
    System.out.println("command-a  constructed.");
  }
  
  @CommandLine.Unmatched
  List<String> unmatched = new ArrayList<>();
  
  @Override
  public Integer call() throws Exception {
    System.out.println("command-a  ran.");
    return null;
  }
}

@CommandLine.Command(name = "command-b")
class CommandB implements Callable<Integer> {
  
  CommandB() {
    System.out.println("command-b  constructed.");
  }
  
  @CommandLine.Unmatched
  List<String> unmatched = new ArrayList<>();
  
  @Override
  public Integer call() throws Exception {
    System.out.println("command-b  ran.");
    return null;
  }
}

@CommandLine.Command(name = "command-c")
class CommandC implements Callable<Integer> {
  
  CommandC() {
    System.out.println("command-c  constructed.");
  }
  
  @CommandLine.Unmatched
  List<String> unmatched = new ArrayList<>();
  
  @Override
  public Integer call() throws Exception {
    System.out.println("command-c  ran.");
    return null;
  }
  
}