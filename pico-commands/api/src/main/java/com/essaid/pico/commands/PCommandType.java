package com.essaid.pico.commands;

import com.essaid.pico.commands.internal.PCommandTypeInternal;

public interface PCommandType {
  String getName();
  
  String[] getPaths();
  
  String getDescription();
  
  Class<? extends PCommand> getType();
  
  PCommand createCommand();
  
  PCommandTypeInternal internal();


}
