package com.essaid.picocli.commands;

import java.lang.reflect.Constructor;
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
    if(command == null){
      Constructor<? extends Callable<Integer>> declaredConstructor = getCommandClass().getDeclaredConstructor();
      declaredConstructor.setAccessible(true);
      command = (K) declaredConstructor.newInstance();
    }
    inject(command);
    return command;
  }
}
