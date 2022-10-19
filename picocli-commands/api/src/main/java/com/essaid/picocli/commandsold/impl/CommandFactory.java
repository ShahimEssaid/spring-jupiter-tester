package com.essaid.picocli.commandsold.impl;

import com.essaid.picocli.commandsold.ICommandFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

public class CommandFactory implements ICommandFactory {
  public Class<? extends Callable<Integer>> getCommandClass() {
    return commandClass;
  }
  
  protected final Class<? extends Callable<Integer>> commandClass;
  protected final String beanNameOrQualifier;
  protected final boolean createExternal;
  
  public CommandFactory(Class<? extends Callable<Integer>> commandClass, String beanNameOrQualifier,
                        boolean createExternal) {
    this.commandClass = commandClass;
    this.beanNameOrQualifier = beanNameOrQualifier;
    this.createExternal = createExternal;
  }
  
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    return create();
  }
  
  @Override
  public <T> T create() {
    try {
      return (T) getCommandClass().getConstructor().newInstance();
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
