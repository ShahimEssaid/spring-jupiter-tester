package com.essaid.pico.commands;

import picocli.CommandLine;

public interface PCommandFactory {

  PCommand createCommand(PCommandType commandType);

}
