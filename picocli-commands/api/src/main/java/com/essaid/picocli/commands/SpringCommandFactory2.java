package com.essaid.picocli.commands;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;

import java.util.Objects;
import java.util.concurrent.Callable;

public class SpringCommandFactory2 extends CommandFactory {
  
  private final boolean createExternal;
  
  public String getQualifier() {
    return qualifier;
  }
  
  public ApplicationContext getContext() {
    return context;
  }
  
  private final String qualifier;
  private final ApplicationContext context;
  
  public SpringCommandFactory2(ApplicationContext context, Class<? extends Callable<Integer>> commandClass,
                               String qualifier, boolean createExternal) {
    super(commandClass);
    this.context = context;
    this.qualifier = qualifier;
    this.createExternal = createExternal;
  }
  
  @Override
  public <K> K create(Class<K> cls) throws Exception {
    K k = super.create(cls);
    if(Objects.nonNull(k)) return  k;
    
    Object command = null;
    
    try {
      if (Objects.nonNull(getQualifier())) {
        command = BeanFactoryAnnotationUtils.qualifiedBeanOfType(getContext(), getCommandClass(), getQualifier());
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
    
    inject(command);
    return (K) command;
  }
}
