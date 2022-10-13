package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.AbstractPCommand;
import com.essaid.picocli.commands.AbstractPCommandType;
import picocli.CommandLine;

@CommandLine.Command(name = "pico-root")
public class RootPCommandType extends AbstractPCommandType {
  
  RootPCommandType() {
    super("pico-root", "Pico root command.", new String[]{"pico-root"}, RootPCommand.class);
  }
  
  static class RootPCommand extends AbstractPCommand {
  
    @Override
    public CommandLine getCommandLine() {
      return null;
    }
  }
  
}
