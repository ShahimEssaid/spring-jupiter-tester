package com.essaid.pico.commands;

import com.essaid.pico.commands.internal.PCommandTypeInternal;

public abstract class AbstractPCommandType implements PCommandTypeInternal {
  
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
  
  @Override
  public PCommands getPCommands() {
    return commands;
  }
  
  @Override
  public void setPCommands(PCommands commands) {
    if (this.commands != null) {
      throw new IllegalStateException("Commands object already set on: " + this);
    }
    this.commands = commands;
  }
  
  @Override
  public PCommand createCommand() {
    PCommand command = getPCommands().getCommandFactory().createCommand(this);
    return command;
  }
  
  @Override
  public PCommandTypeInternal internal() {
    return this;
  }
}
