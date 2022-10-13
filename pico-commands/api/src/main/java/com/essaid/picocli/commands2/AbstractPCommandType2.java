package com.essaid.picocli.commands2;

import com.essaid.picocli.commands2.internal.PCommandType2Internal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPCommandType2 implements PCommandType2Internal {
  
  private static final Logger logger = LoggerFactory.getLogger(AbstractPCommandType2.class);
  
  private final String name;
  private final String description;
  private final String[] paths;
  //private final Constructors constructors;
  private final Class<?> commandLineClass;
  private PCommands2 commands;
  
  protected AbstractPCommandType2(String name, String description, String[] paths, Class<?> commandLineClass) {
    this.name = name;
    this.description = description;
    this.paths = paths;
    this.commandLineClass = commandLineClass;
    //this.constructors = new Constructors(commandLineClass);
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  @Override
  public String[] getPaths() {
    return paths;
  }
  
  @Override
  public String getDescription() {
    return description;
  }
  
  @Override
  public Class<?> getCommandLineClass() {
    return commandLineClass;
  }
  
  protected CommandLine createCommandLine(Object... args) throws InvocationTargetException, InstantiationException,
      IllegalAccessException {
    PCommands2.Environment environment = getCommands().getEnvironment();
    Map<Object, Object> factoryContext = new HashMap<>();
    PCommandFactory2 commandFactory = environment.getCommandFactory(this, factoryContext, args);
    return new CommandLine(getCommandLineClass(), commandFactory);
  }
  
  @Override
  public PCommandType2Internal internal() {
    return this;
  }
  
  @Override
  public void setCommands(PCommands2 commands) {
    this.commands = commands;
  }
  
  @Override
  public PCommands2 getCommands() {
    return commands;
  }
}
