package com.essaid.picocli.commands;

import com.essaid.picocli.commands.internal.PCommandTypeInternal;

public interface PCommandType {
  String getName();
  
  String[] getPaths();
  
  String getDescription();
  
  Class<? extends PCommand> getType();
  
  PCommand createCommand();
  
  PCommandTypeInternal internal();


}
