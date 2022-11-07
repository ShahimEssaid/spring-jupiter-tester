package com.essaid.context.spring;

import com.essaid.context.spring.impl.Factory;
import com.essaid.context.spring.impl.Scope;
import org.springframework.context.ConfigurableApplicationContext;

public interface IFactory {
  
  static IFactory DEFAULT_FACTORY = new Factory();
  
  IDomain createContextDomain(String domainName,
      IScope applicationScope, IFactory factory, IConfig config);
  
  IContainer createApplicationContext(IDomain contextDomain, ConfigurableApplicationContext context, IThreadManager threadManager,  IConfig config);
  
  Scope createScope(IContainer applicationContext, String scopeName, int order, IScope parent, IConfig config,
      IScope... relatedScopes);
  
  IScope createApplicationScope(IScopeContext applicationScopeContext,  IConfig config);
  
  IScopeContext createScopeContext(IScope scope);
  
  
  IConfig createConfig(IConfig parentConfig);
  
  
  /// older
  
  IThreadContextList createThreadContextList();
  
  IThreadContext createThreadContext(IContainer container);
  
  
  IThreadManager createThreadManager();
}
