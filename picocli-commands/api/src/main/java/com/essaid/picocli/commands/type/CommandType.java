package com.essaid.picocli.commands.type;

import com.essaid.picocli.commands.ICommands;
import picocli.CommandLine;

import java.util.List;
import java.util.stream.Collectors;

public class CommandType implements ICommandType.IICommandType {
  
  private final ICommands commands;
  private final String name;
  private final String path;
  private final int order;
  private final String title;
  private final String shortDescription;
  private final String longDescription;
  private final String notes;
  private final Class<?> commandClass;
  private final CommandLine.IFactory factory;
  private final CommandLine.IExecutionStrategy strategy;
  
  public CommandType(ICommands commands, String name, String path, int order, String title, String shortDescription,
                     String longDescription, String notes, Class<?> commandClass, CommandLine.IFactory factory,
                     CommandLine.IExecutionStrategy strategy) {
    this.commands = commands;
    this.name = name;
    this.path = path;
    this.order = order;
    this.title = title;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.notes = notes;
    this.commandClass = commandClass;
    this.factory = factory;
    this.strategy = strategy;
  }
  
  @Override
  public ICommands getCommands() {
    return commands;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String getPath() {
    return path;
  }
  
  @Override
  public int getOrder() {
    return order;
  }
  
  @Override
  public String getTitle() {
    return title;
  }
  
  @Override
  public String getShortDescription() {
    return shortDescription;
  }
  
  @Override
  public String getLongDescription() {
    return longDescription;
  }
  
  public String getNotes() {
    return notes;
  }
  
  @Override
  public Class<?> getCommandClass() {
    return commandClass;
  }

  @Override
  public CommandLine.IFactory getCommandFactory() {
    return factory;
  }
  
  @Override
  public CommandLine.IExecutionStrategy getExecutionStrategy() {
    return strategy;
  }
  
  @Override
  public IICommandType internal() {
    return this;
  }
  
  
  @Override
  public boolean isTopCommand() {
    String directParentPath = getDirectParentPath();
    return !internal().getCommands().internal().getCommandTypesByPath().containsKey(directParentPath);
  }
  
  @Override
  public String getDirectParentPath() {
    int index = path.indexOf(".");
    if (index == -1) {
      return null;
    } else {
      return path.substring(0, index);
    }
  }
  
  @Override
  public List<String> getDirectChildPaths() {
    List<String> childPaths = commands.internal()
        .getCommandTypesByPath()
        .keySet()
        .stream()
        .filter(s -> s.startsWith(getPath()) && !s.equals(getPath()))
        .map(path -> path.substring(getPath().length()))
        .collect(Collectors.toList());
    
    return childPaths;
  }
  
  
  @Override
  public String toString() {
    return "ICommandType[name: " + getName() + ", path: " + getPath() + ", commandClass: " + getCommandClass() + "]";
    
  }
  
  @Override
  public int compareTo(ICommandType o) {
    return getOrder() - o.getOrder();
  }
}
