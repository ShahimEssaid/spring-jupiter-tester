package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import com.essaid.context.spring.IThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class Factory implements IFactory.IFactoryInternal {
  
  private static final Logger logger = LoggerFactory.getLogger(Factory.class);
  
  @Override
  public IDomain createContextDomain(String domainName, IFactory factory, IThreadManager threadManager, IConfig config) {
    return new Domain(domainName, factory, threadManager, config);
  }
  
  @Override
  public IContainer createApplicationContext(IDomain domain, ConfigurableApplicationContext context, IConfig config) {
    return new Container(domain, context, config);
  }
  
  @Override
  public IScope createApplicationScope(IDomain domain, IContainer container, IConfig config) {
    return new SingletonContextScope(domain, container, IContainer.APPLICATION_NAME, IContainer.APPLICATION_ORDER, null, config,
        this);
  }
  
  @Override
  public IScope createContainerScope(IDomain domain, IContainer container, IConfig config) {
    return new SingletonContextScope(domain, container,IContainer.CONTAINER_NAME, IContainer.CONTAINER_ORDER, domain.internal().getApplicationScope(), config,
        this);
  }
  
  @Override
  public IScopeContext createScopeContext(IScope scope) {
    ScopeContext scopeContext = new ScopeContext(scope, scope.internal().generateScopeContextId());
    scope.internal().scopeContextCreated(scopeContext);
    return scopeContext;
  }
  
  @Override
  public IConfig createConfig(IConfig parentConfig) {
    return new Config(parentConfig);
  }
  
  // =====================  old
  
  @Override
  public Scope createScope(IDomain domain,IContainer container, String scopeName, int order, IScope parent, IConfig config,
      IScope... relatedScopes) {
    return new Scope(domain,container, scopeName, order, parent, config, relatedScopes);
  }
  
  
  @Override
  public IThreadContextList createThreadContextList() {
    return new ThreadContextList();
  }
  
  @Override
  public IThreadContext createThreadContext(IDomain domain, IConfig config) {
    ThreadContext threadContext = new ThreadContext(domain);
    threadContext.addScopeContexts(false, config, domain.internal().getApplicationScope().internal().getScopeContext());
    return threadContext;
  }
  
  @Override
  public IThreadManager createThreadManager() {
    return new ThreadManager();
  }
  
  @Override
  public IFactoryInternal internal() {
    return this;
  }
}
