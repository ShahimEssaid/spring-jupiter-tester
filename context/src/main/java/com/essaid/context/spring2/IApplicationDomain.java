package com.essaid.context.spring2;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;

public interface IApplicationDomain  extends IScope, IScopeContext, ApplicationListener<ApplicationContextEvent> {
  
  IThreadContext getThreadContext(boolean create);
  
  IThreadContext setThreadContext(IThreadContext context, boolean overwrite);
  
  IThreadContext removeThreadContext();
  
  void initialize();
  
  IFactory getFactory();
  
  boolean isAutoThreadContext();
  
  boolean isAutoContext();
  
  boolean isAutoScopeContext();
}
