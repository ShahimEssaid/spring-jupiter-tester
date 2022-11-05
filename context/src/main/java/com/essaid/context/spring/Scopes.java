package com.essaid.context.spring;

import com.essaid.context.spring.impl.ApplicationDomain;
import com.essaid.context.spring.impl.Scope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NamedThreadLocal;

public class Scopes {
  private static final ThreadLocal<IApplicationDomain> domainHolder = new NamedThreadLocal<>(
      "Spring context " + "domain");
  private static final ThreadLocal<IApplicationDomain> inheritableDomainHolder = new NamedThreadLocal<>(
      "Spring " + "inheritable context " + "domain");
  
  private Scopes() {
  }
  
  public static IApplicationDomain getDomain() {
    IApplicationDomain domain = domainHolder.get();
    if (domain == null) {
      domain = inheritableDomainHolder.get();
    }
    return domain;
  }
  
  
  public static IApplicationDomain setDomain(IApplicationDomain domain, boolean isThreadInheritable) {
    IApplicationDomain currentDomain = getDomain();
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
  
  public static IApplicationDomain resetDomain() {
    IApplicationDomain currentDomain = getDomain();
    domainHolder.remove();
    inheritableDomainHolder.remove();
    return currentDomain;
  }
  
  public static IApplicationDomain createDomain(String domainName, boolean autoCreateThreadContext, boolean autoContext,
      boolean autoCreateScopeData, boolean inheritableApplicationScope) {
    return new ApplicationDomain(domainName, autoCreateThreadContext, autoContext, autoCreateScopeData,
        inheritableApplicationScope);
  }
  
  public static IScope createApplicationScope(IApplicationDomain domain, ConfigurableApplicationContext context) {
    IFactory factory = domain.getFactory();
    Scope scope = factory.createScope(IFactory.APPLICATION_NAME, context, IFactory.APPLICATION_ORDER, domain, true,
        null);
    return scope;
  }
  
  
  public static IScope createContainerScope(IApplicationDomain domain, ConfigurableApplicationContext context) {
    IFactory factory = domain.getFactory();
    Scope scope = factory.createScope(IFactory.CONTAINER_NAME, context, IFactory.CONTAINER_ORDER, domain, true, domain);
    return scope;
  }
  
  
  public static IScope createSessionScope(IApplicationDomain domain, ConfigurableApplicationContext context,
      IScope parentContainerScope) {
    IFactory factory = domain.getFactory();
    Scope scope = factory.createScope(IFactory.SESSION_NAME, context, IFactory.SESSION_ORDER, domain, true,
        parentContainerScope);
    return scope;
  }
  
  public static IScope createRequestScope(IApplicationDomain domain, ConfigurableApplicationContext context,
      IScope parentSessionScope) {
    IFactory factory = domain.getFactory();
    Scope scope = factory.createScope(IFactory.REQUEST_NAME, context, IFactory.REQUEST_ORDER, domain, true,
        parentSessionScope);
    return scope;
  }
}
