package com.essaid.pico.commands2.internal;

import com.essaid.pico.commands2.PCommands2;
import picocli.CommandLine;

public interface PCommands2Internal extends PCommands2 {
  CommandLine createCommandLine(Class<?> commandClass, CommandLine.IFactory factory);
}
