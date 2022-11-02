package com.essaid.context.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public interface ISpringScopeDomain extends ISpringScope, ISpringScopeData,
    ApplicationListener<ApplicationContextEvent> {
  
  String getDomainName();
  
  ISpringThreadContext resetThreadContext();
  
  ISpringThreadContext getThreadContext();
  
  ISpringThreadContext setThreadContext(ISpringThreadContext context);
  
  default ISpringScopeData getScopeData(ISpringScope scope) {
    return getThreadContext().getScopeData(scope);
  }
  
  boolean isAutoCreateScopeData();
  
  boolean isAutoCreateContext();
  
  ISpringScope createScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                           Thread thread, boolean threadInheritable);
  
  ISpringScopeData createScopeData(ISpringScope scope, ISpringThreadContext context, Thread thread);
  
  ISpringThreadContext createContext(Thread thread);
  
}
