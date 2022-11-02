package com.essaid.context.spring;

import org.springframework.core.NamedThreadLocal;

class SpringContextUtil {
  
  
  private static final ThreadLocal<ISpringContextDomain> domainHolder = new NamedThreadLocal<>("Spring context " +
      "domain");
  private static final ThreadLocal<ISpringContextDomain> inheritableDomainHolder =
      new NamedThreadLocal<>("Spring " + "inheritable context " + "domain");
  
  
  static ISpringContextDomain resetDomain() {
    ISpringContextDomain currentDomain = getDomain();
    domainHolder.remove();
    inheritableDomainHolder.remove();
    return currentDomain;
  }
  
  static ISpringContextDomain setDomain(ISpringContextDomain domain, boolean inheritable) {
    ISpringContextDomain currentDomain = getDomain();
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
  
  static ISpringContextDomain getDomain() {
    ISpringContextDomain domain = domainHolder.get();
    if (domain == null) {
      domain = inheritableDomainHolder.get();
    }
    return domain;
  }
  
  static ISpringContextDomain createDomain(String domainName, boolean autoCreateContext, boolean autoCreateScopeData,
                                           boolean inheritableApplicationScope) {
    return new SpringContextDomain(domainName, autoCreateContext, autoCreateScopeData, inheritableApplicationScope);
  }
  
}
