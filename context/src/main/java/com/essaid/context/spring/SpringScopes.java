package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NamedThreadLocal;

public class SpringScopes {
  
  public static final String APPLICATION_NAME = "application";
  public static final int APPLICATION_ORDER = 0;
  public static final String SESSION_NAME = "session";
  public static final int SESSION_ORDER = 1000;
  public static final String CONVERSATION_NAME = "conversation";
  public static final int CONVERSATION_ORDER = 2000;
  public static final String REQUEST_NAME = "request";
  public static final int REQUEST_ORDER = 3000;
  
  private static final ThreadLocal<ISpringScopeDomain> domainHolder = new NamedThreadLocal<>("Spring context " +
      "domain");
  private static final ThreadLocal<ISpringScopeDomain> inheritableDomainHolder = new NamedThreadLocal<>("Spring " +
      "inheritable context " + "domain");
  
  private SpringScopes() {
  }
  
  public static ISpringScope createRequestScope(ISpringScopeDomain domain, ConfigurableApplicationContext context) {
    return domain.createScope(REQUEST_NAME, REQUEST_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringScope createConversationScope(ISpringScopeDomain domain,
                                                     ConfigurableApplicationContext context) {
    return domain.createScope(CONVERSATION_NAME, CONVERSATION_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringScope createSessionScope(ISpringScopeDomain domain, ConfigurableApplicationContext context) {
    return domain.createScope(SESSION_NAME, SESSION_ORDER, context, Thread.currentThread(), true);
  }
  
  public static ISpringScopeDomain createDomain(String domainName, boolean autoCreateContext,
                                                boolean autoCreateScopeData, boolean inheritableApplicationScope) {
    return new SpringScopeDomain(domainName, autoCreateContext, autoCreateScopeData, inheritableApplicationScope);
  }
  
  public static ISpringScopeDomain getDomain() {
    ISpringScopeDomain domain = domainHolder.get();
    if (domain == null) {
      domain = inheritableDomainHolder.get();
    }
    return domain;
  }
  
  public static ISpringScopeDomain setDomain(ISpringScopeDomain domain, boolean inheritable) {
    ISpringScopeDomain currentDomain = getDomain();
    if (domain == null) {
      resetDomain();
    } else {
      if (inheritable) {
        domainHolder.remove();
        inheritableDomainHolder.set(domain);
      } else {
        domainHolder.set(domain);
        inheritableDomainHolder.remove();
      }
    }
    return currentDomain;
  }
  
  public static ISpringScopeDomain resetDomain() {
    ISpringScopeDomain currentDomain = getDomain();
    domainHolder.remove();
    inheritableDomainHolder.remove();
    return currentDomain;
  }
}
