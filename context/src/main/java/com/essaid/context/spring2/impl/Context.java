package com.essaid.context.spring2.impl;

import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IContext;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IScopeContext;
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
  public IScopeContext setScopeContext(IScope scope, IScopeContext context, boolean overwrite) {
    IScopeContext existingContext = getScopeContext(scope, false);
    if (existingContext != null && !overwrite) {
      throw new IllegalStateException(
          "Can't overwrite IScopeContext. Exising context: " + existingContext + ", new context: " + context);
    }
    return scopeContexts.put(scope, context);
  }
  
  @Override
  public IScopeContext removeScopeContext(IScope scope) {
    return scopeContexts.remove(scope);
  }
}
