package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.ICommandFactory;
import com.essaid.picocli.commands.ICommandManager2;
import com.essaid.picocli.commands.ICommandType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CommandManager2Impl implements ICommandManager2 {
  
  private Map<String, List<ICommandType>> commandPaths = new ConcurrentHashMap<>();
  @Override
  public ICommandType addCommand(String path, Class<?> commandClass, ICommandFactory factory) {
    return null;
  }
}
