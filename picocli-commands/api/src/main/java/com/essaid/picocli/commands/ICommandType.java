package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public interface ICommandType {
  
  String getName();
  
  String getPath();
  
  CommandLine.IFactory getFactory();
  
  String title();
  
  String shortDescription();
  
  String longDescription();

  Class<?> getCommandClass();
  
  IICommandType internal();
  
  interface IICommandType extends ICommandType {
    
    ICommands getCommands();
    
    boolean isTopCommand();
    
    String getDirectParentPath();
    
    List<String> getDirectChildPaths();
    
    Map<Object, Object> getContext();
    
  }
}


