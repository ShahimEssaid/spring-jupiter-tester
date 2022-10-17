package com.essaid.picocli.commands;

public interface ICommandManager2 {
  
  ICommandType addCommand(String path, Class<?> commandClass, ICommandFactory factory);
  
}
