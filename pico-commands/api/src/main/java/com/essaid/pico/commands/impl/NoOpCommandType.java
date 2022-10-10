package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PicoAbstractCommandType;
import com.essaid.pico.commands.PicoAbstractCommandInstance;
import com.essaid.pico.commands.PicoCommandType;
import com.essaid.pico.commands.PicoCommandInstance;
import com.essaid.pico.commands.PicoCommands;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NoOpCommandType extends PicoAbstractCommandType {
  
  public static final String NO_OP_COMMAND_NAME = "no-op";
  
  public NoOpCommandType(){
    super(NO_OP_COMMAND_NAME, NO_OP_COMMAND_NAME, new String[]{"picoCommands.no-op"}, NoOpCommandInstance.class);
  }

  
  @CommandLine.Command(name = NoOpCommandType.NO_OP_COMMAND_NAME, subcommandsRepeatable = true)
  public static class NoOpCommandInstance extends PicoAbstractCommandInstance implements Callable<Integer> {
    
    @CommandLine.Unmatched
    private List<String> arguments = new ArrayList<>();
    
    public NoOpCommandInstance(PicoCommandType command) {
      super(command);
    }
  
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
