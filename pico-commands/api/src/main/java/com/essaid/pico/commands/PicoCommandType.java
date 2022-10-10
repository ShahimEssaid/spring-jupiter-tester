package com.essaid.pico.commands;

public interface PicoCommandType<T extends PicoCommandInstance> {
  String getName();
  
  String[] getPaths();
  
  String getDescription();
  
  Class<T> getType();
  
  void setPicoCommands(PicoCommands commands);
  PicoCommands getPicoCommands();
}
