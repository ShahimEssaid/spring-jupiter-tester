package com.essaid.context.spring;

import com.essaid.context.spring.impl.Scope;
import org.springframework.context.ConfigurableApplicationContext;

public interface IFactory {
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
  
  Scope createScope(String scopeName, ConfigurableApplicationContext applicationContext, int order,
      IApplicationDomain domain, boolean threadInheritable, IScope parent);
  
  IScopeContext createScopeContext(IScope scope);
  
  IThreadContext createThreadContext();
  
  IContext createContext();
  
  IStore createStore();
  
  void close(ConfigurableApplicationContext context);
  
  IThreadManager createThreadManager();
}
