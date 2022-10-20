package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface ICommandType2 {
  
  String getName();
  
  String getPath();
  
  CommandLine.IFactory getFactory();
  
  String getInfo();
  
  Class<?> getCommandClass();
  
  IICommandType2 internal();
  
  interface IICommandType2 extends ICommandType2 {
    
    ICommands2 getCommands();
    
    boolean isTopCommand();
    
    String getDirectParentPath();
    
    List<String> getDirectChildPaths();
    
    Map<Object, Object> getContext();
    

    
  }
}


class CommandType implements ICommandType2.IICommandType2 {
  
  private final CommandLine.IFactory factory;
  private final String path;
  private final String name;
  private final ICommands2 commands;
  private final String info;
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  private final Class<?> commandClass;
  
  CommandType(String name, String path, Class<?> commandClass, CommandLine.IFactory factory, ICommands2 commands,
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
  public String getInfo() {
    return info;
  }
  
  @Override
  public IICommandType2 internal() {
    return this;
  }
  
  @Override
  public ICommands2 getCommands() {
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