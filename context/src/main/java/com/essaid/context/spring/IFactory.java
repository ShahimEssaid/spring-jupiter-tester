package com.essaid.context.spring;

import com.essaid.context.spring.impl.Factory;
import com.essaid.context.spring.impl.Scope;
import org.springframework.context.ConfigurableApplicationContext;

public interface IFactory {
  
  IFactory DEFAULT_FACTORY = new Factory();
  
  default IDomain createContextDomain(String domainName) {
    IConfig config = internal().createConfig(null).setAutoCreateScopeContext(true).setAutoCreateThreadContext(true)
        .setAutoCreateThreadContextList(true).setScopeThreadInheritable(true);
    return internal().createContextDomain(domainName, DEFAULT_FACTORY, internal().createThreadManager(), config);
  }
  
  IFactoryInternal internal();
  
  interface IFactoryInternal extends IFactory {
    
    IDomain createContextDomain(String domainName, IFactory factory, IThreadManager threadManager, IConfig config);
    
    IContainer createApplicationContext(IDomain contextDomain, ConfigurableApplicationContext context, IConfig config);
    
    Scope createScope(IDomain domain, IContainer container, String scopeName, int order, IScope parent, IConfig config,
        IScope... relatedScopes);
    
    IScope createApplicationScope(IDomain domain, IContainer container, IConfig config);
    
    IScope createContainerScope(IDomain domain, IContainer container, IConfig config);
    
    IScopeContext createScopeContext(IScope scope);
    
    
    IConfig createConfig(IConfig parentConfig);
    
    IThreadContextList createThreadContextList();
    
    IThreadContext createThreadContext(IDomain domain, IConfig config);
    
    IThreadManager createThreadManager();
    
  }
  
}
