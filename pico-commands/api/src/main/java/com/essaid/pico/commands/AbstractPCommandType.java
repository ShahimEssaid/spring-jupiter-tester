package com.essaid.pico.commands;

public abstract class AbstractPCommandType implements PCommandType {
  
  private final String name;
  private final String description;
  private final String[] paths;
  private final Class<? extends  PCommand> type;
  private PCommands commands;
  
  protected AbstractPCommandType(String name, String description, String[] paths, Class<? extends  PCommand> type){
    this.name = name;
    this.description = description;
    this.paths = paths;
    this.type = type;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getDescription() {
    return description;
  }
  
  @Override
  public String[] getPaths() {
    return paths;
  }
  
  @Override
  public Class<? extends  PCommand> getType() {
    return type;
  }
  
  public PCommands getPicoCommands() {
    return commands;
  }
  
  public void setPicoCommands(PCommands commands) {
    if (this.commands != null) {
      throw new IllegalStateException("Commands object already set on: " + this);
    }
    this.commands = commands;
  }
  
  @Override
  public PCommand createCommand() {
    PCommand command = null;
    return command;
  }
}
