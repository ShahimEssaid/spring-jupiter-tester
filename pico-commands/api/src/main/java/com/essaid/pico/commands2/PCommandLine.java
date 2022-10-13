package com.essaid.pico.commands2;

import picocli.CommandLine;

import java.util.Map;

public class PCommandLine extends CommandLine {
  
  public PCommandLine(Object command, Map<Object, Object> context) {
    super(command);
  }
  
  public PCommandLine(Object command, IFactory factory, Map<Object, Object> context) {
    super(command, factory);
  }
}
