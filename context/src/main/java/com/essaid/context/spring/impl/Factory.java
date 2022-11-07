package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class Factory implements IFactory {
  
  private static final Logger logger = LoggerFactory.getLogger(Factory.class);
  
  @Override
  public IDomain createContextDomain(String domainName, IFactory factory, IConfig config) {
    return new Domain(domainName, factory, config);
  }
  
  @Override
  public IContainer createApplicationContext(IDomain domain, ConfigurableApplicationContext context, IConfig config) {
    return new Container(domain, context, config);
  }
  
  @Override
  public IScope createApplicationScope(IDomain domain, IConfig config) {
    return new SingletonContextScope(domain, IContainer.APPLICATION_NAME, IContainer.APPLICATION_ORDER, null, config,
        this);
  }
  
  @Override
  public IScope createContainerScope(IDomain domain, IScope parent, IConfig config) {
    return new SingletonContextScope(domain, IContainer.CONTAINER_NAME, IContainer.CONTAINER_ORDER, parent, config,
        this);
  }
  
  @Override
  public IScopeContext createScopeContext(IScope scope) {
    ScopeContext scopeContext = new ScopeContext(scope, scope.generateScopeContextId());
    scope.scopeContextCreated(scopeContext);
    return scopeContext;
  }
  
  @Override
  public IConfig createConfig(IConfig parentConfig) {
    return new Config(parentConfig);
  }
  
  // =====================  old
  
  @Override
  public Scope createScope(IDomain domain, String scopeName, int order, IScope parent, IConfig config,
      IScope... relatedScopes) {
    return new Scope(domain, scopeName, order, parent, config, relatedScopes);
  }
  
  
  @Override
  public IThreadContextList createThreadContextList() {
    return new ThreadContextList();
  }
  
  @Override
  public IThreadContext createThreadContext(IDomain domain, IConfig config) {
    ThreadContext threadContext = new ThreadContext(domain);
    threadContext.addScopeContexts(false, config, domain.getApplicationScope().getScopeContext());
    return threadContext;
  }
  
}
