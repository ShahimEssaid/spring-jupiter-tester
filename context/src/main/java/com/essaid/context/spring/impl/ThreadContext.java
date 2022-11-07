package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IThreadContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadContext implements IThreadContext {
  
  private final Map<IScope, String> contextIdOrNames = new HashMap<>();
  private final Map<IScope, IScopeContext> scopeContexts = new ConcurrentHashMap<>();
  private final IDomain domain;
  //private final IContextDomain domain;
  
  public ThreadContext(IDomain domain) {
    this.domain = domain;
  }
  
  @Override
  public IScopeContext getScopeContext(IScope scope, IConfig config) {
    IScopeContext scopeContext = scopeContexts.get(scope);
    if (scopeContext != null) return scopeContext;
    
    synchronized (scopeContexts) {
      scopeContext = scopeContexts.get(scope);
      if (scopeContext != null) return scopeContext;
      if (config.isCreateScopeContext()) {
        scopeContext = domain.getFactory().createScopeContext(scope);
        scopeContexts.put(scope, scopeContext);
        scope.scopeContextCreated(scopeContext);
      }
      return scopeContext;
    }
  }
  
  @Override
  public void addScopeContexts(boolean overwrite, IConfig config, IScopeContext... addedScopeContexts) {
    synchronized (scopeContexts) {
      for (IScopeContext scopeContext : addedScopeContexts) {
        IScopeContext existingContext = scopeContexts.get(scopeContext.getScope());
        if (existingContext != null && !overwrite) {
          throw new IllegalStateException(
              "Can't overwrite IScopeContext. Exising context: " + existingContext + ", new context: " + scopeContext);
        }
        scopeContexts.put(scopeContext.getScope(), scopeContext);
      }
    }
  }
  
  @Override
  public IScopeContext removeScopeContext(IScope scope) {
    synchronized (scopeContexts) {
      return scopeContexts.remove(scope);
    }
  }
  
  @Override
  public Map<IScope, String> getRequestedScopeContextIds() {
    return contextIdOrNames;
  }
}
