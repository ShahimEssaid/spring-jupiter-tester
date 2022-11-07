package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NamedThreadLocal;

public interface IDomain {
  
  String DEFAULT_DOMAIN_NAME = "_default_domain";
  
  String getDomainName();
  
  IDomainInternal internal();
  
  
  IConfig getConfig();
  
  void registerShutdownHook();
  
  IContainer registerSpringContext(ConfigurableApplicationContext context, IConfig config);
  
  interface IDomainInternal extends IDomain {
    
    ThreadLocal<IDomain> domainHolder = new NamedThreadLocal<>("Spring context " + "domain");

//  IThreadManager getThreadManager();
    ThreadLocal<IDomain> inheritableDomainHolder = new NamedThreadLocal<>(
        "Spring " + "inheritable context " + "domain");
    
    static IDomain getDomain() {
      IDomain domain = domainHolder.get();
      if (domain == null) {
        domain = inheritableDomainHolder.get();
      }
      return domain;
    }
    
    static IDomain setDomain(IDomain domain, boolean isThreadInheritable) {
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
    
    static IDomain resetDomain() {
      IDomain currentDomain = getDomain();
      domainHolder.remove();
      inheritableDomainHolder.remove();
      return currentDomain;
    }
    
    IFactory getFactory();
    
    IScope getApplicationScope();
    

    
    IContainer unregisterSpringContext(ConfigurableApplicationContext context);
    
    void initialize();

    void closeDomain();

    
    IThreadManager getThreadManager();
    
  }
  
}
