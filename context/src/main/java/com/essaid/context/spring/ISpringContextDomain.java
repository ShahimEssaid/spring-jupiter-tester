package com.essaid.context.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public interface ISpringContextDomain extends ISpringScope, ISpringScopeData,
    ApplicationListener<ApplicationContextEvent> {
  
  static ISpringContextDomain createDomain(String domainName, boolean autoCreateContext, boolean autoCreateScopeData,
                                           boolean inheritableApplicationScope) {
    return new SpringContextDomain(domainName, autoCreateContext, autoCreateScopeData, inheritableApplicationScope);
  }
  
  static ISpringContextDomain getDomain() {
    return SpringContextUtil.getDomain();
  }
  
  static ISpringContextDomain setDomain(ISpringContextDomain domain, boolean inheritable) {
    return SpringContextUtil.setDomain(domain, inheritable);
  }
  
  static ISpringContextDomain resetDomain() {
    return SpringContextUtil.resetDomain();
  }
  
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
  
  default ISpringScope createRequestScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.REQUEST_NAME, SpringScopes.REQUEST_ORDER, context, Thread.currentThread(), true);
  }
  
  
  default ISpringScope createConversationScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.CONVERSATION_NAME, SpringScopes.CONVERSATION_ORDER, context,
        Thread.currentThread(), true);
  }
  
  
  default ISpringScope createSessionScope(ConfigurableApplicationContext context) {
    return createScope(SpringScopes.SESSION_NAME, SpringScopes.SESSION_ORDER, context, Thread.currentThread(), true);
  }
  
}
