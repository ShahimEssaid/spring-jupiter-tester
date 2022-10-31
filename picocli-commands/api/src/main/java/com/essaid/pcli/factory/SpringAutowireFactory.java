package com.essaid.pcli.factory;

import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

public class SpringAutowireFactory extends SpringFactory {
  
  public SpringAutowireFactory(ApplicationContext applicationContext) {
    this(applicationContext, null);
  }
  
  public SpringAutowireFactory(ApplicationContext applicationContext, CommandLine.IFactory delegateFactory) {
    super(applicationContext, delegateFactory);
  }
  
  @Override
  public <K> K doCreate(Class<K> cls) throws Exception {
    return getApplicationContext().getAutowireCapableBeanFactory().createBean(cls);
  }
}
