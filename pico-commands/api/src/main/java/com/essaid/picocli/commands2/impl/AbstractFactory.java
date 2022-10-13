package com.essaid.picocli.commands2.impl;

import com.essaid.picocli.commands2.PCommandFactory2;
import com.essaid.picocli.commands2.PCommandType2;
import picocli.CommandLine;

import java.util.Map;

public abstract class AbstractFactory implements PCommandFactory2 {
  
  private final PCommandType2 commandType;
  private final Object[] args;
  private final Map<Object, Object> context;
  
  protected CommandLine.IFactory getPicoDefaultFactory() {
    return picoDefaultFactory;
  }
  
  private final CommandLine.IFactory picoDefaultFactory;
  
  protected AbstractFactory(PCommandType2 commandType, Map<Object, Object> context, Object ...args){
    this.commandType = commandType;
    this.args = args;
    this.picoDefaultFactory = CommandLine.defaultFactory();
    this.context = context;
  }
  
  protected PCommandType2 getCommandType() {
    return commandType;
  }
  
  protected Object[] getArgs() {
    return args;
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return null;
  }
  
}
