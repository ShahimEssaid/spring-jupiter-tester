package com.essaid.picocli.commands;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public interface ICommandRegistry {
  
  void addCommandType(ICommandType commandType);
  
  void addCommandTypes(List<ICommandType> commandTypes);
  ICommandType addCommand(String path, Class<? extends Callable<Integer>> commandClass, String qualifier, ICommandFactory factory);
  
  ICommands getCommands();
  
  IICommandRegistry internal();
  
  interface IICommandRegistry extends ICommandRegistry {
    Map<String, List<ICommandType>> getPathCommandTypes();
  }
  
}
