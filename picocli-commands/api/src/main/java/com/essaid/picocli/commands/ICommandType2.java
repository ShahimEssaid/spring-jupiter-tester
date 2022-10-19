package com.essaid.picocli.commands;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface ICommandType2 {
  
  String getName();
  
  String getPath();
  
  ICommandFactory2 getFactory();
  
  String getInfo();
  
  IICommandType2 internal();
  
  interface IICommandType2 extends ICommandType2 {
    
    boolean isDirectParentPathCommandType();
    
    String getDirectParentPath();
    
    List<String> getDirectChildPaths();
    
    Map<Object, Object> getContext();
    
  }
}


class CommandType implements ICommandType2.IICommandType2 {
  
  private final ICommandFactory2 factory;
  private final String path;
  private final String name;
  private final ICommands2 commands;
  private final String info;
  private final Map<Object, Object> context = new ConcurrentHashMap<>();
  
  CommandType(String name, String path, ICommandFactory2 factory, ICommands2 commands, String info) {
    this.name = name;
    this.path = path;
    this.factory = factory;
    this.commands = commands;
    this.info = info;
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
  public ICommandFactory2 getFactory() {
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
  public boolean isDirectParentPathCommandType() {
    return commands.internal().getCommandTypesByPath().containsKey(getDirectParentPath());
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
    return commands.internal()
        .getCommandTypesByPath()
        .keySet()
        .stream()
        .filter(s -> s.startsWith(getPath()) && !s.substring(getPath().length()).contains("."))
        .collect(Collectors.toList());
  }
  
  @Override
  public Map<Object, Object> getContext() {
    return context;
  }
}