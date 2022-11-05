package com.essaid.context.spring2;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;

public interface IApplicationDomain extends IScope, IScopeContext, ApplicationListener<ApplicationContextEvent> {
  
  void initialize();
  
  IFactory getFactory();
  
  IStore getStore();
  
  IThreadManager getThreadManager();
  
  boolean isAutoThreadContext();
  
  boolean isAutoContext();
  
  boolean isAutoScopeContext();
  
  void close();
  
  
}
