package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class CommandType implements ICommandType.IICommandType {
  
  private final CommandLine.IFactory factory;
  private final String path;
  private final String name;
  private final ICommands commands;
  private final String info;
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  private final Class<?> commandClass;
  
  CommandType(String name, String path, Class<?> commandClass, CommandLine.IFactory factory, ICommands commands,
              String info) {
    this.name = name;
    this.path = path;
    this.factory = factory;
    this.commands = commands;
    this.info = info;
    this.commandClass = commandClass;
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
  public CommandLine.IFactory getFactory() {
    return factory;
  }
  
  @Override
  public String title() {
    return null;
  }
  
  @Override
  public String shortDescription() {
    return null;
  }
  
  @Override
  public String longDescription() {
    return null;
  }
  
  @Override
  public IICommandType internal() {
    return this;
  }
  
  @Override
  public ICommands getCommands() {
    return commands;
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
  public Map<Object, Object> getContext() {
    return context;
  }
  
  @Override
  public Class<?> getCommandClass() {
    return commandClass;
  }
  
  @Override
  public String toString() {
    return "ICommandType[name: " + getName() + ", path: " + getPath() + ", commandClass: " + getCommandClass() + "]";
    
  }
}
