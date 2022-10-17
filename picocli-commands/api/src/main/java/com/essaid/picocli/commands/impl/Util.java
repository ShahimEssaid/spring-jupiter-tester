package com.essaid.picocli.commands.impl;

import picocli.CommandLine;

public class Util {
  private Util() {
  }
  
  public static boolean isWithoutSubCommands(Class<?> commandClass) {
    return new CommandLine(commandClass).getSubcommands().isEmpty();
  }
}
