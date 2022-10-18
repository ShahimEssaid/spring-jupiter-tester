package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.Constants;
import com.essaid.picocli.commands.ICommandFactory;
import com.essaid.picocli.commands.ICommandRegistry;
import com.essaid.picocli.commands.ICommandType;
import com.essaid.picocli.commands.ICommands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistryImpl implements ICommandRegistry.IICommandRegistry {
  
  private Map<String, List<ICommandType>> commandsByPath = new ConcurrentHashMap<>();
  
  public CommandRegistryImpl(){
    addCommand(Constants.RESERVED_NO_OP_COMMAND_PATH, NoOpCommand.class, null, new CommandFactory(NoOpCommand.class,
        null , true));
    addCommand(Constants.RESERVED_ROOT_COMMAND_PATH, NoOpCommand.class, null, new CommandFactory(NoOpCommand.class,
        null , true));
  }
  
  @Override
  public void addCommandType(ICommandType commandTypeTemplate) {
    addCommand(commandTypeTemplate.getPath(), commandTypeTemplate.getCommandClass(),
        commandTypeTemplate.getQualifier(), commandTypeTemplate.getFactory());
  }
  
  @Override
  public void addCommandTypes(List<ICommandType> commandTypes) {
    commandTypes.forEach(commandType -> addCommandType(commandType));
  }
  
  @Override
  public ICommandType addCommand(String path, Class<? extends Callable<Integer>> commandClass, String qualifier,
                                 ICommandFactory factory) {
    ICommandType commandType = new CommandType(this, path, commandClass, qualifier, factory);
    List<ICommandType> commandTypesForPath = commandsByPath.computeIfAbsent(path,
        s -> new ArrayList<>());
    commandTypesForPath.add(commandType);
    return commandType;
  }
  
  @Override
  public ICommands getCommands() {
    return new Commands(this);
  }
  
  @Override
  public boolean validate(boolean strict) {
    return false;
  }
  
  @Override
  public IICommandRegistry internal() {
    return this;
  }
  
  @Override
  public Map<String, List<ICommandType>> getPathCommandTypes() {
    return Collections.unmodifiableMap(commandsByPath);
  }
}
