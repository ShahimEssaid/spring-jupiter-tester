package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;

public class SingletonContextScope extends Scope {
  
  private final IScopeContext singletonContext;
  
  public SingletonContextScope(IDomain domain, String scopeName, int scopeOrder, IScope parentScope,
      IConfig config, IFactory factory) {
    super(domain, scopeName, scopeOrder, parentScope, config);
    this.singletonContext = factory.createScopeContext(this);
    this.scopeContextCreated(singletonContext);
  }
  
  @Override
  public IScopeContext getScopeContext() {
    return singletonContext;
  }
}
