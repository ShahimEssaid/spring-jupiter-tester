package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ThreadContext implements IThreadContext {
  
  private final IContainer applicationContext;
  private final Map<IScope, String> contextIdOrNames = new HashMap<>();
  private final Map<IScope, IScopeContext> scopeContexts = new ConcurrentHashMap<>();
  //private final IContextDomain domain;
  
  public ThreadContext(IContainer applicationContext) {
    this.applicationContext = applicationContext;
    //this.domain = domain;
  }
  
  @Override
  public IScopeContext getScopeContext(IScope scope, IConfig config) {
    IScopeContext scopeContext = scopeContexts.get(scope);
    if(scopeContext != null) return scopeContext;
    
    synchronized (scopeContexts){
      scopeContext = scopeContexts.get(scope);
      if(scopeContext != null) return scopeContext;
      if(config.isCreateScopeContext()){
        scopeContext = applicationContext.getFactory().createScopeContext(scope);
        scopeContexts.put(scope, scopeContext);
        scope.scopeContextCreated(scopeContext);
      }
      return scopeContext;
    }
  }
  
  @Override
  public void addScopeContexts(boolean overwrite,IConfig config, IScopeContext... addedScopeContexts) {
    for (IScopeContext scopeContext : addedScopeContexts) {
      IScopeContext existingContext = getScopeContext(scopeContext.getScope(), config);
      if (existingContext != null && !overwrite) {
        throw new IllegalStateException(
            "Can't overwrite IScopeContext. Exising context: " + existingContext + ", new context: " + scopeContext);
      }
      scopeContexts.put(scopeContext.getScope(), scopeContext);
    }
  }
  
  @Override
  public IScopeContext removeScopeContext(IScope scope) {
    return scopeContexts.remove(scope);
  }
  
  @Override
  public Map<IScope, String> getRequestedScopeContextIds() {
    return contextIdOrNames;
  }
}
