package com.essaid.context.spring.impl;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IContext;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Context implements IContext {
  
  private final Map<IScope, String> contextIds = new HashMap<>();
  private final Map<IScope, IScopeContext> scopeContexts = new HashMap<>();
  private final IApplicationDomain domain;
  
  public Context(IApplicationDomain domain) {
    this.domain = domain;
  }
  
  @Override
  public IScopeContext getScopeContext(IScope scope, boolean create) {
    IScopeContext scopeContext = scopeContexts.get(scope);
    if (scopeContext == null && create) {
      scopeContext = domain.getFactory().createScopeContext(scope);
      scopeContexts.put(scope, scopeContext);
    }
    return scopeContext;
  }
  
  @Override
  public void addScopeContexts(boolean overwrite, IScopeContext... addedScopeContexts) {
    for (IScopeContext scopeContext : addedScopeContexts) {
      IScopeContext existingContext = getScopeContext(scopeContext.getScope(), false);
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
    return contextIds;
  }
}
