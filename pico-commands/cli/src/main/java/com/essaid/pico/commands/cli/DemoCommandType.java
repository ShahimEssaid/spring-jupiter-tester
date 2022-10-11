package com.essaid.pico.commands.cli;

import com.essaid.pico.commands.AbstractPCommand;
import com.essaid.pico.commands.AbstractPCommandType;
import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.PCommands;
import picocli.CommandLine;

public class DemoCommandType extends AbstractPCommandType {
  
  public static String DEMO_COMMAND_NAME = PCommands.PICO_COMMAND_PREFIX + ".command.demo";
  
  public DemoCommandType() {
    super(DEMO_COMMAND_NAME, DEMO_COMMAND_NAME, new String[]{DEMO_COMMAND_NAME}, DemoPCommand.class);
  }
  
  
  public static class DemoPCommand extends AbstractPCommand {
    
    DemoPCommand(PCommandType type) {
      super(type);
    }
    
    @Override
    public CommandLine getCommandLine() {
      return null;
    }
  }
}
