package com.essaid.picocli.commands.execute;

import com.essaid.picocli.commands.util.Reflect;
import com.essaid.util.intercept.interceptor.IInterceptor;
import picocli.CommandLine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Callable;

public class Execution implements IExecutionStrategyExtended.IExecution {
  final private IExecutionStrategyExtended strategyExtended;
  final private CommandLine.ParseResult parseResult;
  final private IInterceptor interceptor;
  private final CommandLine commandLine;
  private final Object command;
  
  private Object executionResult;
  
  public Execution(IExecutionStrategyExtended strategyExtended, CommandLine.ParseResult parseResult,
                   IInterceptor interceptor) {
    this.strategyExtended = strategyExtended;
    this.parseResult = parseResult;
    this.commandLine = parseResult.commandSpec().commandLine();
    this.command = commandLine.getCommand();
    this.interceptor = interceptor;
  }
  
  @Override
  public Object getResult() {
    return executionResult;
  }
  
  @Override
  public Object execute() {
    if (isExecuted()) {
      //TODO: throw exception?
      return getResult();
    }
    
    CommandLine.Model.CommandSpec commandSpec = parseResult.commandSpec();
    CommandLine commandLine = commandSpec.commandLine();
    Object command = commandLine.getCommand();
    
    return getResult();
  }
  
  @Override
  public int getExitCode() {
    return 0;
  }
  
  @Override
  public boolean isExitGenerator() {
    return parseResult.commandSpec().commandLine() instanceof CommandLine.IExitCodeGenerator;
  }
  
  @Override
  public boolean isExecuted() {
    return executionResult != null;
  }
  
  protected void doExecute(){
    executionResult = executeUserObject();
  }
  
  protected Object executeUserObject() {
    CommandLine commandLine = parseResult.commandSpec().commandLine();
    Object command = commandLine.getCommand();
    if (command instanceof Runnable) {
      return executeRunnable();
    } else if (command instanceof Callable) {
      executeCallable();
    } else if (command instanceof Method) {
      executeMethod();
    }
    if (commandLine.getSubcommands().isEmpty()) {
      throw new CommandLine.ExecutionException(commandLine, "Parsed command (" + command + ") is not a Method, " +
          "Runnable " + "or" + " Callable");
    } else {
      throw new CommandLine.ParameterException(commandLine, "Missing required subcommand");
    }
  }
  
  protected Object executeRunnable() {

    Runnable runnable = (Runnable) command;
    try {
      runnable.run();
      commandLine.setExecutionResult(null);
      return null;
    } catch (CommandLine.ParameterException ex) {
      throw ex;
    } catch (CommandLine.ExecutionException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new CommandLine.ExecutionException(commandLine, "Error while running command (" + runnable + "): " + ex,
          ex);
    }
  }
  
  protected Object executeCallable() {
    Callable<Object> callable = (Callable<Object>) command;
    try {
      executionResult = callable.call();
      commandLine.setExecutionResult(executionResult);
      return executionResult;
    } catch (CommandLine.ParameterException ex) {
      throw ex;
    } catch (CommandLine.ExecutionException ex) {
      throw ex;
    } catch (Exception ex) {
      throw new CommandLine.ExecutionException(commandLine, "Error while calling command (" + callable + "): " + ex,
          ex);
    }
  }
  
  protected void executeMethod() {
    Method method = (Method) command;
    try {
      Object[] parsedArgs = Reflect.invokeCommandSpec_commandMethodParamValues(commandLine.getCommandSpec());
      //Object[] parsedArgs = parsed.getCommandSpec().commandMethodParamValues();

      if (Modifier.isStatic(method.getModifiers())) {
        executionResult = method.invoke(null, parsedArgs); // invoke static method
      } else if (commandLine.getCommandSpec().parent() != null) {
        executionResult = method.invoke(commandLine.getCommandSpec().parent().userObject(), parsedArgs);
      } else {
        executionResult = method.invoke(Reflect.getCommandLine_factory(commandLine)
            .create(method.getDeclaringClass()), parsedArgs);
      }
      commandLine.setExecutionResult(executionResult);
    } catch (InvocationTargetException ex) {
      Throwable t = ex.getTargetException();
      if (t instanceof CommandLine.ParameterException) {
        throw (CommandLine.ParameterException) t;
      } else if (t instanceof CommandLine.ExecutionException) {
        throw (CommandLine.ExecutionException) t;
      } else {
        throw new CommandLine.ExecutionException(commandLine, "Error while calling command (" + method + "): " + t, t);
      }
    } catch (Exception ex) {
      throw new CommandLine.ExecutionException(commandLine, "Unhandled error while calling command (" + method + "): "
          + ex, ex);
    }
  }
  
}
