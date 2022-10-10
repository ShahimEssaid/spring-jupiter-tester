package com.essaid.pico.commands;

import picocli.CommandLine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PicoAbstractCommandInstance implements  PicoCommandInstance{
  
  @CommandLine.Spec
  private CommandLine.Model.CommandSpec commandSpec;
  
  
  private final PicoCommandType command;
  private Map<Object, Object> context = new ConcurrentHashMap<>();
  
  public PicoAbstractCommandInstance(PicoCommandType command){
    this.command = command;
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
  
  @Override
  public PicoCommandType getCommand() {
    return command;
  }
}
