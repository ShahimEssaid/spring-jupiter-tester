package com.essaid.pico.commands;

public interface PCommandType {
  String getName();
  
  String[] getPaths();
  
  String getDescription();
  
  Class<? extends PCommand> getType();
  
  PCommand createCommand();

}
