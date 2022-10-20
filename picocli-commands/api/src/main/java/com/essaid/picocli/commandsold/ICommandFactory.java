package com.essaid.picocli.commandsold;

import picocli.CommandLine;

public interface ICommandFactory extends CommandLine.IFactory {

  
  
  <T> T create();
}
