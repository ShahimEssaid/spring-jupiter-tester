package com.essaid.pcli.execute;

import com.essaid.pcli.util.Reflect;
import com.essaid.util.intercept.interceptor.IInterceptorList;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractExecutionStrategyExtended implements IExecutionStrategyExtended {
  
  private final CommandLine.ParseResult executionParseResult;
  //private List<Object> commandResults = new ArrayList<>();
  private Map<CommandLine.ParseResult, Object> parseResultResultMap = new LinkedHashMap<>();
  private List<CommandLine.IExitCodeGenerator> exitCodeGenerators = new ArrayList<>();
  private IInterceptorList interceptorList;
  private List<IExecutionStrategyExtended> children = new ArrayList<>();
  
  private List<ICommandExecution> executions;
  private Iterator<ICommandExecution> executionIterator;
  
  
  public AbstractExecutionStrategyExtended(CommandLine.ParseResult parseResult) {
    this.executionParseResult = parseResult;
  }
  
  @Override
  final public int execute(CommandLine.ParseResult parseResult) throws CommandLine.ExecutionException,
      CommandLine.ParameterException {
    return execute(parseResult, false);
  }
  
  final public int execute(CommandLine.ParseResult parseResult, boolean withHelp) throws CommandLine.ExecutionException, CommandLine.ParameterException {
    if (executionParseResult != parseResult) {
      throw new IllegalArgumentException("Exection strategy called with parse result that it was not configured to " +
          "execute. Call parse result:" + parseResult);
    }
    
    CommandLine.IExecutionStrategy executionStrategy = parseResult.commandSpec().commandLine().getExecutionStrategy();
    if (executionStrategy != this) {
      throw new IllegalArgumentException("Execution strategy called on a parse result that has a different strategy.");
    }
    
    if (withHelp) {
      Integer helpExitCode = executeHelpRequest(parseResult);
      if (helpExitCode != null) {
        return helpExitCode;
      }
    }
    
    if (executionIterator == null) {
      executionIterator = iterator();
      executions = new ArrayList<>();
    }
    
    while (executionIterator.hasNext()) {
      executeNext();
    }
    return 0;
  }
  
  @Override
  public Integer executeNext() {
    if (!executionIterator.hasNext()) {
      // TODO: throw exception?
      return resolveExitCode();
    }
  
    ICommandExecution next = iterator().next();
    next.execute();
    executions.add(next);
    
    // todo
//    CommandLine.IExecutionStrategy nextStrategy = nextParseResult.commandSpec().commandLine().getExecutionStrategy();
//
//    if (!(nextStrategy instanceof IExecutionStrategyExtended)) {
//      throw new IllegalStateException("This execution strategy needs IExecutionStrategyExtended children but " +
//          "found:" + nextStrategy);
//    }
//
//    IExecutionStrategyExtended extendedNextStrategy = (IExecutionStrategyExtended) nextStrategy;
//    if (this != nextStrategy) {
//      children.add(extendedNextStrategy);
//      extendedNextStrategy.next();
//    }
    

    
    // todo: Integer exitCode = executeUserObject(nextParseResult);
    
    // todo: return exitCode;
    return  0;
  }
  
  protected int returnExitCode() {
    return 0;
  }
  
  //abstract protected int doExecute(CommandLine.ParseResult parseResult);
  
  protected Integer executeHelpRequest(CommandLine.ParseResult parseResult) {
    return executeHelpRequest(parseResult.asCommandLineList());
  }
  
  protected Integer executeHelpRequest(List<CommandLine> parsedCommands) {
    for (CommandLine parsed : parsedCommands) {
      CommandLine.Help.ColorScheme colorScheme = parsed.getColorScheme();
      PrintWriter out = parsed.getOut();
      if (parsed.isUsageHelpRequested()) {
        parsed.usage(out, colorScheme);
        return parsed.getCommandSpec().exitCodeOnUsageHelp();
      } else if (parsed.isVersionHelpRequested()) {
        parsed.printVersionHelp(out, Reflect.getCommandLine_Help_ColorScheme_ansi(colorScheme));
        return parsed.getCommandSpec().exitCodeOnVersionHelp();
      } else if (parsed.getCommandSpec().helpCommand()) {
        PrintWriter err = parsed.getErr();
        if (((Object) parsed.getCommand()) instanceof CommandLine.IHelpCommandInitializable2) {
          ((CommandLine.IHelpCommandInitializable2) parsed.getCommand()).init(parsed, colorScheme, out, err);
        } else if (((Object) parsed.getCommand()) instanceof CommandLine.IHelpCommandInitializable) {
          ((CommandLine.IHelpCommandInitializable) parsed.getCommand()).init(parsed,
              Reflect.getCommandLine_Help_ColorScheme_ansi(colorScheme), System.out, System.err);
        }
        //todo: executeUserObject(parsed.getParseResult());
        return parsed.getCommandSpec().exitCodeOnUsageHelp();
      }
    }
    return null;
  }
  

  
  
  
  private int resolveExitCode() {
    int result = 0;
    
    for (CommandLine.IExitCodeGenerator generator : exitCodeGenerators) {
      try {
        int exitCode = generator.getExitCode();
        if ((exitCode > 0 && exitCode > result) || (exitCode < result && result <= 0)) {
          result = exitCode;
        }
      } catch (Exception ex) {
        result = (result == 0) ? 1 : result;
        ex.printStackTrace();
      }
    }
    for (Object obj : parseResultResultMap.values()) {
      if (obj instanceof Integer) {
        int exitCode = (Integer) obj;
        if ((exitCode > 0 && exitCode > result) || (exitCode < result && result <= 0)) {
          result = exitCode;
        }
      }
    }
    
    return result == 0 ? executionParseResult.commandSpec().exitCodeOnSuccess() : result;
  }
  
  
  private int resolveExitCode(CommandLine.IExitCodeGenerator generator, Integer result, int exitCodeOnSuccess) {
    int resultExitCode = 0;
    if (generator != null) {
      try {
        int generatorExitCode = generator.getExitCode();
        if ((generatorExitCode > 0 && generatorExitCode > resultExitCode) || (generatorExitCode < resultExitCode && resultExitCode <= 0)) {
          resultExitCode = generatorExitCode;
        }
      } catch (Exception ex) {
        resultExitCode = (resultExitCode == 0) ? 1 : resultExitCode;
        ex.printStackTrace();
      }
    }
    
    if (result != null) {
      if ((result > 0 && result > resultExitCode) || (result < resultExitCode && resultExitCode <= 0)) {
        result = result;
      }
    }
    
    return resultExitCode == 0 ? exitCodeOnSuccess : resultExitCode;
  }
}
