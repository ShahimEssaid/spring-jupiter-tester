package com.essaid.pcli.execute;

import picocli.CommandLine;

import java.util.List;

public interface IExecutionStrategyExtended extends CommandLine.IExecutionStrategy,
    Iterable<ICommandExecution> {
  
  
  int execute(CommandLine.ParseResult parseResult, boolean withHelp);
  
  Integer executeNext();
  
  List<Object> getResults();
  
  /**
   * Returns the list of generators this strategy instance executed. This is useful for a "parent" strategy to
   * aggregate all generators before returning the final exist code.
   *
   * @return
   */
  List<CommandLine.IExitCodeGenerator> getExitCodeGenerators();
  
  int getExitCode();
  
}
