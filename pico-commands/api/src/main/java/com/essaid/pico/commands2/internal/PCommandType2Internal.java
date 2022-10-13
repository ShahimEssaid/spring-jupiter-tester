package com.essaid.pico.commands2.internal;

import com.essaid.pico.commands2.PCommandType2;
import com.essaid.pico.commands2.PCommands2;

public interface PCommandType2Internal  extends PCommandType2  {
  
  void setCommands(PCommands2 commands);
  
  PCommands2 getCommands();
  
}
