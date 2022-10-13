package com.essaid.pico.commands2;

import com.essaid.pico.commands2.internal.PCommandType2Internal;
import picocli.CommandLine;

public interface PCommandType2 {
  String getName();
  
  String[] getPaths();
  
  String getDescription();
  
  Class<?> getCommandLineClass();
  
  CommandLine createCommandLine();
  
  PCommandType2Internal internal();


}
