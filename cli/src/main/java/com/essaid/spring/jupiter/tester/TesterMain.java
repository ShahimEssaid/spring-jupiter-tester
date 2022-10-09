package com.essaid.spring.jupiter.tester;

import com.essaid.spring.jupiter.tester.fhirclient.FhirclientCommand;
import com.essaid.spring.jupiter.tester.jupiter.JupiterCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(subcommandsRepeatable = true)
public class TesterMain implements Callable {
  public static void main(String[] args) {
    
    int exitCode = new TesterMain().run(args);
    System.exit(exitCode);
  }
  
  private int run(String[] args) {
    CommandLine commandLine = new CommandLine(this);
    
    JupiterCommand jupiterCommand = new JupiterCommand();
    commandLine.addSubcommand(jupiterCommand);
    
    FhirclientCommand fhirclientCommand = new FhirclientCommand();
    commandLine.addSubcommand(fhirclientCommand);
    
    CommandLine.ParseResult parseResult = commandLine.parseArgs(args);
    return new CommandLine.RunAll().execute(parseResult);
  }
  
  @Override
  public Object call() throws Exception {
    return null;
  }
}