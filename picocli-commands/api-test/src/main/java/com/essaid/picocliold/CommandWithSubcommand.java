package com.essaid.picocliold;

import picocli.CommandLine;

@CommandLine.Command(name = "command", subcommands = CommandWithSubcommand.SubCommand.class)
public class CommandWithSubcommand {
  
  @CommandLine.Command(name = "subcommand")
  public static class SubCommand{
  
  }
}
