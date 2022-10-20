package com.essaid.picocli.commandsold.tmp;

import com.essaid.picocli.commands.picocli.CommandLine;

@CommandLine.Command(name = "NonCallableCommandExample")
public class NonCallableCommandExample {
  
  public static void main(String[] args) {
    
    CommandLine commandLine = new CommandLine(NonCallableCommandExample.class);
    commandLine.execute(args);
    
  }
}
