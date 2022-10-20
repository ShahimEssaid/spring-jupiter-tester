package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commands.picocli.CommandLine;

public class Util {
  private Util() {
  }
  
  public static boolean isWithoutSubCommands(Class<?> commandClass) {
    return new CommandLine(commandClass).getSubcommands().isEmpty();
  }
}
