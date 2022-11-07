package com.essaid.context.spring;

import org.springframework.core.NamedThreadLocal;

public class Scopes {
  private static final ThreadLocal<IDomain> domainHolder = new NamedThreadLocal<>("Spring context " + "domain");
  private static final ThreadLocal<IDomain> inheritableDomainHolder = new NamedThreadLocal<>(
      "Spring " + "inheritable context " + "domain");
  
  private Scopes() {
  }
  
  public static IDomain getDomain() {
    IDomain domain = domainHolder.get();
    if (domain == null) {
      domain = inheritableDomainHolder.get();
    }
    return domain;
  }
  
  
  public static IDomain setDomain(IDomain domain, boolean isThreadInheritable) {
    IDomain currentDomain = getDomain();
    if (domain == null) {
      resetDomain();
    } else {
      if (isThreadInheritable) {
        domainHolder.remove();
        inheritableDomainHolder.set(domain);
      } else {
        domainHolder.set(domain);
        inheritableDomainHolder.remove();
      }
    }
    return currentDomain;
  }
  
  public static IDomain resetDomain() {
    IDomain currentDomain = getDomain();
    domainHolder.remove();
    inheritableDomainHolder.remove();
    return currentDomain;
  }

}
