package com.essaid.context.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

public interface IContainer extends ApplicationListener<ApplicationEvent> {
  
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
  
  IScope getContainerScope();
  
  IScope getScope(String scopeName);
  
  IScope createScope(String scopeName, int order, IScope parent, IScope... relatedScopes);
  
  boolean isInitialized();

  
  boolean isClosed();
  
  IConfig getConfig();
  
  default void createDefaultScopes() {
    IScope sessionScope = createScope(IContainer.SESSION_NAME, IContainer.SESSION_ORDER, getContainerScope());
    IScope requestScope = createScope(IContainer.REQUEST_NAME, IContainer.REQUEST_ORDER, sessionScope);
  }
  
  IThreadContextList getThreadContextList();
  
  IThreadContext getThreadContex();
}
