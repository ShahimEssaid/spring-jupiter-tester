package com.essaid.picocli.commandsold;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public interface ICommandRegistry {
  
  void addCommandType(ICommandType commandType);
  
  void addCommandTypes(List<ICommandType> commandTypes);
  ICommandType addCommand(String path, Class<? extends Callable<Integer>> commandClass, String qualifier, ICommandFactory factory);
  
  ICommands getCommands();
  
  boolean validate(boolean strict);
  
  IICommandRegistry internal();
  
  interface IICommandRegistry extends ICommandRegistry {
    
    static String DEFAULT_REGISTRY_NAME = "_default";
    
    static Map<String, ICommandRegistry> registries = new ConcurrentHashMap<>();
    
    Map<Object, Object> getRegistryCache();
    
    Map<String, List<ICommandType>> getPathCommandTypes();
  }
  
}
