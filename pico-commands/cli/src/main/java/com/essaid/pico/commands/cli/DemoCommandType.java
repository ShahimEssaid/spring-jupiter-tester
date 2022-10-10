package com.essaid.pico.commands.cli;

import com.essaid.pico.commands.PicoAbstractCommandInstance;
import com.essaid.pico.commands.PicoAbstractCommandType;
import com.essaid.pico.commands.PicoCommandInstance;
import com.essaid.pico.commands.PicoCommandType;
import picocli.CommandLine;

public class DemoCommandType extends PicoAbstractCommandType<DemoCommandType.DemoCommandInstance> {
  
  public DemoCommandType(){
    super("picoCommands.demo", "Pico demo command", new String[]{"picoCommands.demo"}, DemoCommandInstance.class);
  }
  

  public static class DemoCommandInstance extends PicoAbstractCommandInstance{
  
    DemoCommandInstance(PicoCommandType type){
      super(type);
    }
    @Override
    public CommandLine getCommandLine() {
      return null;
    }
  }
}
