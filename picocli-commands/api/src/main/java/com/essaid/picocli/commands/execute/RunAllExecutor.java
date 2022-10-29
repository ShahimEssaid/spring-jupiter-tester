package com.essaid.picocli.commands.execute;

import picocli.CommandLine;

import java.util.Iterator;
import java.util.List;

public class RunAllExecutor extends AbstractExecutionStrategyExtended implements CommandLine.IExecutionStrategy {
  
  
  public RunAllExecutor(CommandLine.ParseResult parseResult) {
    super(parseResult);
  }
  
  @Override
  public List<Object> getResults() {
    return null;
  }
  
  @Override
  public List<CommandLine.IExitCodeGenerator> getExitCodeGenerators() {
    return null;
  }
  
  @Override
  public int getExitCode() {
    return 0;
  }
  
  @Override
  public Iterator<IExecution> iterator() {
    return null;
  }
}
