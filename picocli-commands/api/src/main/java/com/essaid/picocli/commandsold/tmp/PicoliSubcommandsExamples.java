package com.essaid.picocli.commandsold.tmp;

import com.essaid.picocli.commands.picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

public class PicoliSubcommandsExamples {
  
  public static void main(String[] args) {
    CommandLine cl = new CommandLine(A.class);
    cl.addSubcommand("", A_C.class);
    
    cl.setExecutionStrategy(new CommandLine.RunAll());
    
    cl.execute(new String[]{"a", "abc", "a"});
  }
}


@CommandLine.Command(name = "a", subcommandsRepeatable = false,
subcommands = A_B.class)
class A implements Callable<Integer> {
  {
    System.out.println(getClass() + " constructed.");
  }
  
  @CommandLine.Unmatched
  List<String> unmatched;
  
  @Override
  public Integer call() throws Exception {
    System.out.println("Running: " + getClass() + "  "+ unmatched);
    return null;
  }
}

@CommandLine.Command(name = "a_b", subcommandsRepeatable = true)
class A_B implements Callable<Integer> {
  {
    System.out.println(getClass() + " constructed.");
  }
  
  @Override
  public Integer call() throws Exception {
    System.out.println("Running: " + getClass());
    return null;
  }
}


@CommandLine.Command(name = "a", subcommandsRepeatable = true, aliases = {"cc", "a"})
class A_C implements Callable<Integer> {
  {
    System.out.println(getClass() + " constructed.");
  }
  
  @Override
  public Integer call() throws Exception {
    System.out.println("Running: " + getClass());
    return null;
  }
}