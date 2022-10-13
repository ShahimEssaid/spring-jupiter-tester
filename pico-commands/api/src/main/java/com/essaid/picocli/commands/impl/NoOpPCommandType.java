package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.AbstractPCommandType;
import com.essaid.picocli.commands.AbstractPCommand;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NoOpPCommandType extends AbstractPCommandType {
  
  public static final String NO_OP_COMMAND_NAME = "picoCommands.no-op";
  
  public NoOpPCommandType(){
    super(NO_OP_COMMAND_NAME, NO_OP_COMMAND_NAME, new String[]{NO_OP_COMMAND_NAME}, NoOpPCommand.class);
  }

  
  @CommandLine.Command(name = NoOpPCommandType.NO_OP_COMMAND_NAME, subcommandsRepeatable = true)
  public static class NoOpPCommand extends AbstractPCommand implements Callable<Integer> {
    
    @CommandLine.Unmatched
    private List<String> arguments = new ArrayList<>();
    
    @Override
    public CommandLine getCommandLine() {
      return new CommandLine(this);
    }
  
    public List<String> getArguments() {
      return arguments;
    }
  
    @Override
    public Integer call() throws Exception {
      return 0;
    }
  }
}
