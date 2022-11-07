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

public class Factory implements IFactory {
  
  private static final Logger logger = LoggerFactory.getLogger(Factory.class);
  
  @Override
  public IDomain createContextDomain(String domainName,
      IScope applicationScope,IFactory factory, IConfig config) {
    return new Domain(domainName,   applicationScope,factory, config);
  }
  
  @Override
  public IContainer createApplicationContext(IDomain domain, ConfigurableApplicationContext context,IThreadManager threadManager, IConfig config) {
    return new Container(domain, context, threadManager, config);
  }
  
  @Override
  public IScope createApplicationScope(IScopeContext applicationScopeContext,  IConfig config) {
    return new ApplicationScope(applicationScopeContext, config);
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
  public Scope createScope(IContainer applicationContext, String scopeName, int order, IScope parent, IConfig config,
      IScope... relatedScopes) {
    return new Scope(applicationContext, scopeName, order, parent, config, relatedScopes);
  }
  
  
  @Override
  public IThreadContextList createThreadContextList() {
    return new ThreadContextList();
  }
  
  @Override
  public IThreadContext createThreadContext(IContainer container) {
    return new ThreadContext(container);
  }
  
  @Override
  public IThreadManager createThreadManager() {
    return new ThreadManager();
  }
}
