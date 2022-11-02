package com.essaid.context.spring;

import org.springframework.core.NamedThreadLocal;

public class SpringThreadManager {
  private static final ThreadLocal<ISpringContext> contextHolder = new NamedThreadLocal<>("Spring " + "context");
  private static final ThreadLocal<ISpringContext> inheritableContextHolder = new NamedThreadLocal<>("Spring " +
      "inheritable context");
  
  private static final ThreadLocal<ISpringContextDomain> domainHolder = new NamedThreadLocal<>("Spring context " +
      "domain");
  private static final ThreadLocal<ISpringContextDomain> inheritableDomainHolder =
      new NamedThreadLocal<>("Spring " + "inheritable context " + "domain");
  
  public static void resetContext() {
    contextHolder.remove();
    inheritableContextHolder.remove();
  }
  
  public static void setContext(ISpringContext threadContext, boolean inheritable) {
    if (threadContext == null) {
      resetContext();
    } else {
      if (inheritable) {
        contextHolder.remove();
        inheritableContextHolder.set(threadContext);
      } else {
        contextHolder.set(threadContext);
        inheritableContextHolder.remove();
      }
    }
  }
  
  public static ISpringContext getContext() {
    ISpringContext context = contextHolder.get();
    if (context == null) {
      context = inheritableContextHolder.get();
    }
    if(context == null){
      ISpringContextDomain domain = getDomain();
      if(domain.isAutoCreateContexts()){
        context = domain.createContext(Thread.currentThread());
        setContext(context);
      }
    }
    return context;
  }
  
  public static void setContext(ISpringContext threadContext) {
    setContext(threadContext, false);
  }
  
  
  public static void resetDomain() {
    domainHolder.remove();
    inheritableDomainHolder.remove();
  }
  
  public static void setDomain(ISpringContextDomain domain, boolean inheritable) {
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
  }
  
  public static ISpringContextDomain getDomain() {
    ISpringContextDomain domain = domainHolder.get();
    if (domain == null) {
      domain = inheritableDomainHolder.get();
    }
    return domain;
  }
  
  public static void setDomain(ISpringContextDomain domain) {
    setDomain(domain, false);
  }
  
}
