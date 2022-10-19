package com.essaid.picocli.commands;

import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public interface ICommandFactory2 extends CommandLine.IFactory {
  
  
  IICommandFactory2 internal();
  
  interface IICommandFactory2 extends ICommandFactory2 {
    
    void setCommandType(ICommandType2 commandType);
    
    void inject(Object commandObject);
    
  }
}

abstract class CommandFactory implements ICommandFactory2.IICommandFactory2 {
  private ICommandType2 commandType;
  
  public Class<? extends Callable<Integer>> getCommandClass() {
    return commandClass;
  }
  
  private final Class<? extends Callable<Integer>> commandClass;
  
  CommandFactory(Class<? extends Callable<Integer>> commandClass) {
    this.commandClass = commandClass;
  }
  
  @Override
  public IICommandFactory2 internal() {
    return this;
  }
  
  @Override
  public void setCommandType(ICommandType2 commandType) {
    this.commandType = commandType;
  }
  
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    if (cls.isInterface()) {
      if (Collection.class.isAssignableFrom(cls)) {
        if (List.class.isAssignableFrom(cls)) {
          return cls.cast(new ArrayList<Object>());
        } else if (SortedSet.class.isAssignableFrom(cls)) {
          return cls.cast(new TreeSet<Object>());
        } else if (Set.class.isAssignableFrom(cls)) {
          return cls.cast(new LinkedHashSet<Object>());
        } else if (Queue.class.isAssignableFrom(cls)) {
          return cls.cast(new LinkedList<Object>()); // ArrayDeque is only available since 1.6
        } else {
          return cls.cast(new ArrayList<Object>());
        }
      }
      if (SortedMap.class.isAssignableFrom(cls)) {
        return cls.cast(new TreeMap<Object, Object>());
      }
      if (Map.class.isAssignableFrom(cls)) {
        return cls.cast(new LinkedHashMap<Object, Object>());
      }
    }
    return null;
  }
  
  @Override
  public void inject(Object commandObject) {
    if (commandObject instanceof ICommand) {
      ICommand command = (ICommand) commandObject;
      command.setCommandType(commandType);
    }
  }
}

