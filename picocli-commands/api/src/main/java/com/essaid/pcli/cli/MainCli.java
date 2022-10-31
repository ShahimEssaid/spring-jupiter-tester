package com.essaid.pcli.cli;

import picocli.CommandLine;

import java.util.Arrays;

public class MainCli {
  
  public static void main(String[] args) {
    MainCommand main = new MainCommand(args);
    CommandLine commandLine = new CommandLine(main);
    //commandLine.setExpandAtFiles(false);
    CommandLine.ParseResult parseResult = commandLine.parseArgs(args);
    System.out.println("Main id: " + main.getMainId());
    System.out.println("Main id: " + main.otherArgs);
    System.out.println("Main id: " + Arrays.asList(main.cliArgs()));
    
  }
}
