package com.essaid.picocli.commandsold.impl;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;
import java.util.concurrent.Callable;

public class SpringCommandFactory extends CommandFactory {
  
  private final ConfigurableApplicationContext context;
  
  public SpringCommandFactory(Class<? extends Callable<Integer>> commandClass, String nameNameOrQualifier,
                              boolean createExternal, ConfigurableApplicationContext context) {
    super(commandClass, nameNameOrQualifier, createExternal);
    this.context = context;
  }
  
  
  @Override
  public <T> T create() {
    Object command = null;
    
    try {
      if (Objects.nonNull(beanNameOrQualifier)) {
        command = BeanFactoryAnnotationUtils.qualifiedBeanOfType(context, getCommandClass(), beanNameOrQualifier);
      } else {
        command = context.getBean(getCommandClass());
      }
    } catch (NoSuchBeanDefinitionException e) {
      if (createExternal) {
        command = context.getAutowireCapableBeanFactory().createBean(getCommandClass());
      } else {
        throw e;
      }
    }
    
    return (T) command;
  }
}
