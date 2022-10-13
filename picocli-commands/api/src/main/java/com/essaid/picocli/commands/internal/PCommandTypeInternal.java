package com.essaid.picocli.commands.internal;

import com.essaid.picocli.commands.PCommandType;
import com.essaid.picocli.commands.PCommands;

public interface PCommandTypeInternal extends PCommandType {
  
  void setPCommands(PCommands pCommands);
  
  PCommands getPCommands();
  
}
