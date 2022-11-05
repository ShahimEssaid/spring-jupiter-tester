package com.essaid.context.spring.impl;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store implements IStore {
  private final IApplicationDomain domain;
  private Map<IScope, List<IScopeContext>> contexts = new HashMap<>();
  
  public Store(IApplicationDomain domain) {
    this.domain = domain;
  }
  
  @Override
  public void created(IScopeContext context) {
    contexts.computeIfAbsent(context.getScope(), s -> new ArrayList<>()).add(context);
  }
  
  @Override
  public void close(IScope scope) {
    List<IScopeContext> scopeContexts = contexts.remove(scope);
    if (scopeContexts != null) {
      scopeContexts.forEach(sc -> sc.close());
    }
  }
}
