package com.essaid.pico.commands2.exception;

import com.essaid.pico.commands2.PCommandType2;
import com.essaid.pico.commands2.PCommands2;

public class ExistingCommandTypeName extends CommandsException {
  private final PCommandType2 existing;
  private final PCommandType2 duplicate;
  
  public String getName() {
    return name;
  }
  
  private final String name;
  
  public ExistingCommandTypeName(String name, PCommandType2 existing, PCommandType2 duplicate, String message){
    super(message);
    this.name = name;
    this.existing = existing;
    this.duplicate = duplicate;
  }
  
  public PCommandType2 getExisting() {
    return existing;
  }
  
  public PCommandType2 getDuplicate() {
    return duplicate;
  }
}
