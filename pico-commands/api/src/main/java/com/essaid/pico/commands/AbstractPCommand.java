package com.essaid.pico.commands;

import com.essaid.pico.commands.internal.PCommandInternal;
import picocli.CommandLine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractPCommand implements PCommandInternal {
  
  
  private PCommandType commandType;
  private Map<Object, Object> context = new ConcurrentHashMap<>();
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
  
  @Override
  public PCommandType getPCommandType() {
    return commandType;
  }
  
  @Override
  public void setPCommandType(PCommandType type) {
    this.commandType = type;
  }
  
  @Override
  public PCommandInternal internal() {
    return this;
  }
}
