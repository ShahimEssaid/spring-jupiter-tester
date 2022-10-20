package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commandsold.CommandInfo;
import com.essaid.picocli.commandsold.ICommandFactory;
import com.essaid.picocli.commandsold.ICommandRegistry;
import com.essaid.picocli.commandsold.ICommandType;
import com.essaid.picocli.commandsold.ICommands;
//import org.semver4j.Semver;
import com.essaid.picocli.commands.picocli.CommandLine;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

public class CommandType implements ICommandType.IICommandType {
  private final String path;
  private final Class<? extends Callable<Integer>> commandClass;
  private final String qualifier;
  private final CommandInfo commandInfo;
  private final CommandLine.Command commandAnnotation;
  private final ICommandFactory factory;
  private final ICommandRegistry manager;
  private final ICommands commands;
  
  public CommandType(String newPath, ICommandType commandTypeTemplate, ICommands commands) {
    this.path = newPath == null ? commandTypeTemplate.getPath() : newPath;
    this.commandClass = commandTypeTemplate.getCommandClass();
    this.qualifier = commandTypeTemplate.getQualifier();
    this.commandInfo = commandTypeTemplate.internal().getCommandInfoAnnotation();
    this.commandAnnotation = commandTypeTemplate.internal().getCommandAnnotation();
    this.factory = commandTypeTemplate.getFactory();
    this.manager = commandTypeTemplate.getManager();
    this.commands = commands;
  }
  
  
  public CommandType(ICommandRegistry manager, String path, Class<? extends Callable<Integer>> commandClass,
                     String qualifier, ICommandFactory factory) {
    this.manager = manager;
    this.path = path;
    this.commandClass = commandClass;
    this.qualifier = qualifier;
    this.commandInfo = commandClass.getAnnotation(CommandInfo.class);
    this.commandAnnotation = commandClass.getAnnotation(CommandLine.Command.class);
    if (commandAnnotation == null) {
      throw new IllegalStateException("Class does not have @Command annotation while creating !CommandType. Class: " + commandClass);
    }
    this.factory = factory;
    this.commands = null;
  }
  
  
  @Override
  public String getPath() {
    return path;
  }
  
  @Override
  public Class<? extends Callable<Integer>> getCommandClass() {
    return commandClass;
  }
  
  @Override
  public String getQualifier() {
    return qualifier;
  }
  
  @Override
  public String getGroupId() {
    return commandInfo != null ? commandInfo.groupId() : commandClass.getPackageName();
  }
  
  @Override
  public String getArtifactId() {
    return commandInfo != null ? commandInfo.artifactId() : commandClass.getSimpleName();
  }
//
//  @Override
//  public Semver getVersion() {
//    return commandInfo != null ? new Semver(commandInfo.commandVersion()) : new Semver("0.0.0");
//  }
  
  @Override
  public String getCommandId() {
    return commandAnnotation.name();
  }
  
  @Override
  public ICommandFactory getFactory() {
    return factory;
  }
  
  @Override
  public ICommandRegistry getManager() {
    return manager;
  }
  
  @Override
  public IICommandType internal() {
    return this;
  }
  
  @Override
  public int compareTo(ICommandType o) {
    int order = Comparator.comparing(ICommandType::getPath)
        .thenComparing(ICommandType::getGroupId)
        .thenComparing(ICommandType::getArtifactId)
        .thenComparing(ICommandType::getCommandId)
        //.thenComparing(ICommandType::getVersion)
        .compare(this, o);
    return order;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CommandType that)) return false;
    return path.equals(that.path) && commandClass.equals(that.commandClass) && Objects.equals(qualifier,
        that.qualifier) && Objects.equals(commandInfo, that.commandInfo) && commandAnnotation.equals(that.commandAnnotation);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(path);
  }
  
  @Override
  public CommandLine.Command getCommandAnnotation() {
    return commandAnnotation;
  }
  
  @Override
  public CommandInfo getCommandInfoAnnotation() {
    return commandInfo;
  }
  
  @Override
  public Map<Object, Object> getCache() {
    return null;
  }
  
}
