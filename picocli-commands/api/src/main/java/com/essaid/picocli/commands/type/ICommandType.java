package com.essaid.picocli.commands.type;

import com.essaid.picocli.commands.ICommands;
import picocli.CommandLine;

import java.util.List;

public interface ICommandType extends Comparable<ICommandType> {
  
  String getName();
  
  String getPath();
  
  int getOrder();
  
  
  String getTitle();
  
  String getShortDescription();
  
  String getLongDescription();
  
  String getNotes();
  
  Class<?> getCommandClass();
  
  CommandLine.IFactory getCommandFactory();
  
  CommandLine.IExecutionStrategy getExecutionStrategy();
  
  
  IICommandType internal();
  
  interface IICommandType extends ICommandType {
    
    ICommands getCommands();
    
    boolean isTopCommand();
    
    String getDirectParentPath();
    
    List<String> getDirectChildPaths();
    
  }
}


