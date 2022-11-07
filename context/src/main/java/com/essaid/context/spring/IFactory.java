package com.essaid.context.spring;

import com.essaid.context.spring.impl.Factory;
import com.essaid.context.spring.impl.Scope;
import org.springframework.context.ConfigurableApplicationContext;

public interface IFactory {
  
  static IFactory DEFAULT_FACTORY = new Factory();
  
  default IDomain createContextDomain() {
    IConfig config = createConfig(null).setAutoCreateScopeContext(true).setAutoCreateThreadContext(true)
        .setAutoCreateThreadContextList(true).setScopeThreadInheritable(true);
    return createContextDomain(IDomain.DEFAULT_DOMAIN_NAME, DEFAULT_FACTORY, config);
  }
  
  IDomain createContextDomain(String domainName, IFactory factory, IConfig config);
  
  IContainer createApplicationContext(IDomain contextDomain, ConfigurableApplicationContext context,
       IConfig config);
  
  Scope createScope(IDomain domain, String scopeName, int order, IScope parent, IConfig config,
      IScope... relatedScopes);
  
  IScope createApplicationScope(IDomain domain, IConfig config);
  
  IScope createContainerScope(IDomain domain, IScope parent, IConfig config);
  
  IScopeContext createScopeContext(IScope scope);
  
  
  IConfig createConfig(IConfig parentConfig);
  
  
  /// older
  
  IThreadContextList createThreadContextList();
  
  IThreadContext createThreadContext(IDomain domain, IConfig config);
  
}
