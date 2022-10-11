package com.essaid.pico.commands;

import picocli.CommandLine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPCommand implements PCommand {
  
  
  private final PCommandType commandType;
  private Map<Object, Object> context = new ConcurrentHashMap<>();
  
  public AbstractPCommand(PCommandType commandType){
    this.commandType = commandType;
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
  
  @Override
  public PCommandType getType() {
    return commandType;
  }

}
