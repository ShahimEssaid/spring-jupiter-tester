package com.essaid.pico.commands;

public abstract class PicoAbstractCommandType<T extends PicoAbstractCommandInstance> implements PicoCommandType<T> {
  
  private final String name;
  private final String description;
  private final String[] paths;
  private final Class<T> type;
  private PicoCommands commands;
  
  protected  PicoAbstractCommandType(String name, String description, String[] paths, Class<T> type){
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
  public Class<T> getType() {
    return type;
  }
  
  @Override
  public PicoCommands getPicoCommands() {
    return commands;
  }
  
  @Override
  public void setPicoCommands(PicoCommands commands) {
    if (this.commands != null) {
      throw new IllegalStateException("Commands object already set on: " + this);
    }
    this.commands = commands;
  }
  

}
