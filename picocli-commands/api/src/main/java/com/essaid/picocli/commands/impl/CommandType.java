package com.essaid.picocli.commands.impl;

import com.essaid.picocli.commands.CommandInfo;
import com.essaid.picocli.commands.ICommandFactory;
import com.essaid.picocli.commands.ICommandType;
import org.semver4j.Semver;
import picocli.CommandLine;

import java.util.Comparator;

public class CommandType implements ICommandType.IICommandType{
  private final String path;
  private final Class<?> commandClass;
  private final CommandInfo commandInfoAnnotation;
  private final CommandLine.Command commandAnnotation;
  private final ICommandFactory factory;
  
  
  public CommandType(String path, Class<?> commandClass, ICommandFactory factory) {
    this.path = path;
    this.commandClass = commandClass;
    this.commandInfoAnnotation = commandClass.getAnnotation(CommandInfo.class);
    this.commandAnnotation = commandClass.getAnnotation(CommandLine.Command.class);
    if (commandAnnotation == null) {
      throw new IllegalStateException("Class does not have @Command annotation while creating !CommandType. Class: " + commandClass);
    }
    this.factory = factory;
  }
  
  
  
  @Override
  public String getPath() {
    return path;
  }
  
  @Override
  public Class<?> getCommandClass() {
    return commandClass;
  }
  
  @Override
  public String getGroupId() {
    return commandInfoAnnotation != null ? commandInfoAnnotation.groupId() : commandClass.getName();
  }
  
  @Override
  public String getArtifactId() {
    return commandInfoAnnotation != null ? commandInfoAnnotation.artifactId() : commandClass.getSimpleName();
  }
  
  @Override
  public Semver getVersion() {
    return commandInfoAnnotation != null ? new Semver(commandInfoAnnotation.version()) : new Semver("0.0.0");
  }
  
  @Override
  public String getCommandId() {
    return commandAnnotation.name();
  }
  
  @Override
  public ICommandFactory getFactory() {
    return factory;
  }
  
  @Override
  public IICommandType internal() {
    return this;
  }
  
  @Override
  public int compareTo(ICommandType o) {
   int order =  Comparator.comparing(ICommandType::getPath)
        .thenComparing(ICommandType::getGroupId)
        .thenComparing(ICommandType::getArtifactId)
        .thenComparing(ICommandType::getCommandId)
        .thenComparing(ICommandType::getVersion).compare(this, o);
    return order;
  }
}
