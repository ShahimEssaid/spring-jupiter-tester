package com.essaid.pico.commands.impl;

import com.essaid.pico.commands.PicoAbstractCommandInstance;
import com.essaid.pico.commands.PicoAbstractCommandType;
import com.essaid.pico.commands.PicoCommandInstance;
import com.essaid.pico.commands.PicoCommandType;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "pico-root")
public class RootCommandType extends PicoAbstractCommandType<RootCommandType.RootCommandInstance> {
  
  RootCommandType() {
    super("pico-root", "Pico root command.", new String[]{"pico-root"}, RootCommandInstance.class);
  }
  
  static class RootCommandInstance  extends PicoAbstractCommandInstance {
  
    public RootCommandInstance(PicoCommandType command) {
      super(command);
    }
  
    @Override
    public CommandLine getCommandLine() {
      return null;
    }
  }
  
}
