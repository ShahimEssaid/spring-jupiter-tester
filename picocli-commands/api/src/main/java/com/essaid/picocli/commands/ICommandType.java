package com.essaid.picocli.commands;

import org.semver4j.Semver;
import picocli.CommandLine;

import java.util.Map;
import java.util.concurrent.Callable;

public interface ICommandType extends Comparable<ICommandType> {
  
  String getPath();
  
  Class<? extends Callable<Integer>> getCommandClass();
  
  String getQualifier();
  
  String getGroupId();
  
  String getArtifactId();
  
  Semver getVersion();
  
  String getCommandId();
  
  ICommandFactory getFactory();
  
  ICommandRegistry getManager();
  
  /*
   
   */
  IICommandType internal();
  
  interface IICommandType extends ICommandType {
    CommandLine.Command getCommandAnnotation();
    
    CommandInfo getCommandInfoAnnotation();
    
    Map<Object, Object> getCache();

  }
  
}
