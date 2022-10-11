package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.AbstractPCommand;
import com.essaid.pico.commands.AbstractPCommandType;
import com.essaid.pico.commands.PCommandType;
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
