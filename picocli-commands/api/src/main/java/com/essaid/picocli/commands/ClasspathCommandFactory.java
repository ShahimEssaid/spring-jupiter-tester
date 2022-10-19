package com.essaid.picocli.commands;

import java.util.Objects;
import java.util.concurrent.Callable;

public class ClasspathCommandFactory extends CommandFactory {
  
  private final Objects[] args;
  
  public ClasspathCommandFactory(Class<? extends Callable<Integer>> commandClass, Objects... constructorArgs) {
    super(commandClass);
    this.args = constructorArgs;
  }
  
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    K command = super.create(cls);
    inject(command);
    return command;
  }
}
