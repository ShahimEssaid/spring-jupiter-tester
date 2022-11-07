package com.essaid.context.spring;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public interface IContainer extends ApplicationListener<SpringApplicationEvent> {
  
  String APPLICATION_NAME = "application";
  int APPLICATION_ORDER = 0;
  String CONTAINER_NAME = "container";
  int CONTAINER_ORDER = 1000;
  String SESSION_NAME = "session";
  int SESSION_ORDER = 2000;
  String CONVERSATION_NAME = "conversation";
  int CONVERSATION_ORDER = 3000;
  String REQUEST_NAME = "request";
  int REQUEST_ORDER = 4000;
  
  IDomain getDomain();
  
  ConfigurableApplicationContext getSpringContext();
  
  IScope getScope(String scopeName);
  
  IScope createScope(String scopeName, int order, IScope parent,
      boolean autoCreateScopeContext, boolean threadInheritable, IScope... relatedScopes);
  
  boolean isInitialized();
  IFactory getFactory();
  
  boolean isClosed();
  
  IConfig getConfig();
  
  IThreadManager getThreadManager();
}
