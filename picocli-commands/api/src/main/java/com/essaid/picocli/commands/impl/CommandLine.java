package com.essaid.picocli.commands.impl;

public class CommandLine extends picocli.CommandLine {
  
  CommandLine(Object command) {
    super(command);
  }
  
  CommandLine(Object command, IFactory factory) {
    super(command, factory);
  }
}
