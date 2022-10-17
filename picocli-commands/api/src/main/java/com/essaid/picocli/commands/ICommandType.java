package com.essaid.picocli.commands;

import org.semver4j.Semver;

public interface ICommandType extends Comparable<ICommandType> {
  
  String getPath();
  
  Class<?> getCommandClass();
  
  String getGroupId();
  
  String getArtifactId();
  
  Semver getVersion();
  
  String getCommandId();
  
  ICommandFactory getFactory();
  
  /*
   
   */
  IICommandType internal();
  
  interface IICommandType extends ICommandType {
  
  }
  
}
