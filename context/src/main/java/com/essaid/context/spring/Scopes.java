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

//  public static IDomain createDomain(String domainName, boolean autoCreateThreadContext, boolean autoContext,
//      boolean autoCreateScopeData, boolean inheritableApplicationScope) {
//    return new Domain(domainName, autoCreateThreadContext, autoContext, autoCreateScopeData,
//        inheritableApplicationScope);
//  }
//
//  public static IScope createApplicationScope(IDomain domain, ConfigurableApplicationContext context) {
//    IFactory factory = domain.getFactory();
//    Scope scope = factory.createScope(IContainer.APPLICATION_NAME, context, IContainer.APPLICATION_ORDER, domain, true,
//        null);
//    return scope;
//  }


//  public static IScope createContainerScope(IDomain domain, ConfigurableApplicationContext context) {
//    IFactory factory = domain.getFactory();
//    Scope scope = factory.createScope(IContainer.CONTAINER_NAME, context, IContainer.CONTAINER_ORDER, domain, true, domain);
//    return scope;
//  }
//
//
//  public static IScope createSessionScope(IDomain domain, ConfigurableApplicationContext context,
//      IScope parentContainerScope) {
//    IFactory factory = domain.getFactory();
//    Scope scope = factory.createScope(IContainer.SESSION_NAME, context, IContainer.SESSION_ORDER, domain, true,
//        parentContainerScope);
//    return scope;
//  }
//
//  public static IScope createRequestScope(IDomain domain, ConfigurableApplicationContext context,
//      IScope parentSessionScope) {
//    IFactory factory = domain.getFactory();
//    Scope scope = factory.createScope(IContainer.REQUEST_NAME, context, IContainer.REQUEST_ORDER, domain, true,
//        parentSessionScope);
//    return scope;
//  }
}
