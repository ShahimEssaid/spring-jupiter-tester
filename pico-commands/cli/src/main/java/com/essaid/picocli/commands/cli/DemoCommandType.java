package com.essaid.picocli.commands.cli;

import com.essaid.picocli.commands.AbstractPCommand;
import com.essaid.picocli.commands.AbstractPCommandType;
import com.essaid.picocli.commands.PCommands;
import picocli.CommandLine;

import java.util.concurrent.Callable;

public class DemoCommandType extends AbstractPCommandType {
  
  public static String DEMO_COMMAND_NAME = PCommands.PICO_COMMAND_PREFIX + ".command.demo";
  
  public DemoCommandType() {
    super(DEMO_COMMAND_NAME, DEMO_COMMAND_NAME, new String[]{DEMO_COMMAND_NAME}, DemoPCommand.class);
  }
  
  
  public static class DemoPCommand extends AbstractPCommand {
    
    @Override
    public CommandLine getCommandLine() {
      DemoPCommandLine demoPCommandLine = new DemoPCommandLine();
      demoPCommandLine.setCommand(this);
      return new CommandLine(demoPCommandLine);
    }
  }
  
  @CommandLine.Command(name = "demo-command")
  public static class DemoPCommandLine implements Callable<Integer> {
    
    private DemoPCommand command;
    
    @Override
    public Integer call() throws Exception {
      command.getContext().put(PCommands.PICO_COMMAND_OUTPUT, DEMO_COMMAND_NAME);
      return 0;
    }
    
    public void setCommand(DemoPCommand demoPCommand) {
      this.command = demoPCommand;
    }
  }
}
