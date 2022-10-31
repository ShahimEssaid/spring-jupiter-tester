package com.essaid.pcli.factory;

import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

import java.util.Objects;

public class SpringFactory extends AbstractFactory {
  
  private final ApplicationContext applicationContext;
  
  public SpringFactory(ApplicationContext applicationContext) {
    this(applicationContext, null);
  }
  
  public SpringFactory(ApplicationContext applicationContext, CommandLine.IFactory delegateFactory) {
    super(delegateFactory);
    if (Objects.isNull(applicationContext)) {
      throw new IllegalArgumentException("Spring factory can't be constructed with null ApplicationContext.");
    }
    this.applicationContext = applicationContext;
  }
  
  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }
  
  @Override
  public <K> K doCreate(Class<K> cls) throws Exception {
    return applicationContext.getBean(cls);
  }
  
  
}
