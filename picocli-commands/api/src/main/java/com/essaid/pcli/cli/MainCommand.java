package com.essaid.pcli.cli;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "internal-main-command")
public class MainCommand implements Main {
  
  private final String[] args;
  @CommandLine.Option(names = {"--main-id"}, description = "Specify the main/root command id to use when parsing" +
      " and " +
      "executing the full command line.")
  String mainId;
  
  @CommandLine.Unmatched
  List<String> otherArgs;
  
  
  public MainCommand(String[] args) {
    this.args = args;
  }
  
  @Override
  public String[] cliArgs() {
    return args;
  }
  
  @Override
  public String getMainId() {
    return mainId;
  }
}
