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
  
  default ISpringScope createRequestScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.REQUEST_NAME, SpringScopes.REQUEST_ORDER, context, null);
  }
  
  
  default ISpringScope createConversationScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.CONVERSATION_NAME, SpringScopes.CONVERSATION_ORDER, context, null);
  }
  
  
  default ISpringScope createSessionScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.SESSION_NAME, SpringScopes.SESSION_ORDER, context, null);
  }
  
}
