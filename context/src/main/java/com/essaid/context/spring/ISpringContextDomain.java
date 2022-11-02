package com.essaid.context.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public interface ISpringContextDomain extends ISpringScope, ISpringScopeData,
    ApplicationListener<ApplicationContextEvent> {
  
  String getDomainName();
  
  ISpringContext resetThreadContext();
  
  ISpringContext getThreadContext();
  
  ISpringContext setThreadContext(ISpringContext context);
  
  default ISpringScopeData getScopeData(ISpringScope scope) {
    return getThreadContext().getScopeData(scope);
  }
  
  boolean isAutoCreateScopeData();
  
  boolean isAutoCreateContext();
  
  ISpringScope createScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                           Thread thread, boolean threadInheritable);
  
  ISpringScopeData createScopeData(ISpringScope scope, ISpringContext context, Thread thread);
  
  ISpringContext createContext(Thread thread);
  
}
