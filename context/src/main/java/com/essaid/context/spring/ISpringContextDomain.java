package com.essaid.context.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public interface ISpringContextDomain extends ISpringScope, ISpringScopeData,
    ApplicationListener<ApplicationContextEvent> {
  
  boolean isAutoCreateScopes();
  
  boolean isAutoCreateContexts();
  
  ISpringScope createScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                           Thread thread);
  
  ISpringScopeData createScopeData(ISpringScope scope, ISpringContext context, Thread thread);
  
  ISpringContext createContext(Thread thread);
}
