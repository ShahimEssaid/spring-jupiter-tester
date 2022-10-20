package com.essaid.picocli.commandsold;

import com.essaid.picocli.commands.picocli.CommandLine;

public interface ICommandFactory extends CommandLine.IFactory {

  
  
  <T> T create();
}
