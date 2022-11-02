package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

public class SpringScopes {
  
  public static final String APPLICATION_NAME = "application";
  public static final int APPLICATION_ORDER = 0;
  public static final String SESSION_NAME = "session";
  public static final int SESSION_ORDER = 1000;
  public static final String CONVERSATION_NAME = "conversation";
  public static final int CONVERSATION_ORDER = 2000;
  public static final String REQUEST_NAME = "request";
  public static final int REQUEST_ORDER = 3000;
  
  private SpringScopes() {
  }
  
  public static ISpringScope createRequestScope(ISpringContextDomain domain, ConfigurableApplicationContext context) {
    return domain.createScope(REQUEST_NAME, REQUEST_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringScope createConversationScope(ISpringContextDomain domain,
                                                     ConfigurableApplicationContext context) {
    return domain.createScope(CONVERSATION_NAME, CONVERSATION_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringScope createSessionScope(ISpringContextDomain domain, ConfigurableApplicationContext context) {
    return domain.createScope(SESSION_NAME, SESSION_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringContextDomain createDomain(String domainName, boolean autoCreateContext,
                                                  boolean autoCreateScopeData, boolean inheritableApplicationScope) {
    return SpringContextUtil.createDomain(domainName, autoCreateContext, autoCreateScopeData,
        inheritableApplicationScope);
  }
  
  public static ISpringContextDomain getDomain() {
    return SpringContextUtil.getDomain();
  }
  
  public static ISpringContextDomain setDomain(ISpringContextDomain domain, boolean inheritable) {
    return SpringContextUtil.setDomain(domain, inheritable);
  }
  
  public static ISpringContextDomain resetDomain() {
    return SpringContextUtil.resetDomain();
  }
}
