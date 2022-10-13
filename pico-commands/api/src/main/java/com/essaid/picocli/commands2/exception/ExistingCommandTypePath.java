package com.essaid.picocli.commands2.exception;

import com.essaid.picocli.commands2.PCommandType2;

public class ExistingCommandTypePath extends CommandsException {
  private final PCommandType2 existing;
  private final PCommandType2 duplicate;
  
  public String getName() {
    return name;
  }
  
  private final String name;
  
  public ExistingCommandTypePath(String name, PCommandType2 existing, PCommandType2 duplicate, String message){
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
