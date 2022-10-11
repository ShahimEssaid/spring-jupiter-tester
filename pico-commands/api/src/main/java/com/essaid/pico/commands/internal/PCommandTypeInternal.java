package com.essaid.pico.commands.internal;

import com.essaid.pico.commands.PCommandType;
import com.essaid.pico.commands.PCommands;

public interface PCommandTypeInternal extends PCommandType {
  
  void setPCommands(PCommands pCommands);
  
  PCommands getPCommands();
  
}
