package com.essaid.context.spring.impl;

import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store implements IStore.IStoreInternal {
  private final IDomain domain;
  private final Map<IScope, List<IScopeContext>> createdScopeContexts = new HashMap<>();
  
  private final Map<String, IScopeContext> idScopeContextMap = new HashMap<>();
  
  public Store(IDomain domain) {
    this.domain = domain;
  }
  
  @Override
  public void created(IScopeContext context) {
    
    
    createdScopeContexts.computeIfAbsent(context.internal().getScope(), s -> new ArrayList<>()).add(context);
  }
  
  @Override
  public List<IScopeContext> getCreated(IScope scope) {
    return createdScopeContexts.get(scope);
  }
  
  @Override
  public Map<IScope, List<IScopeContext>> getCreated() {
    return createdScopeContexts;
  }

//  @Override
//  public IScopeContext save(IScopeContext context, boolean overwrite) {
//    String scopeContextId = context.getScopeContextId();
//    String savedId = context.getSavedId();
//    IScopeContext saved = getSaved(scopeContextId);
//    if (saved == context) return null;
//    if (saved != null && !overwrite) {
//      throw new IllegalStateException(
//          "Scope context id: " + scopeContextId + " being saved as context: " + context + " but different context exists and no overwrite:" + saved);
//    }
//    return idScopeContextMap.put(scopeContextId, context);
//  }
  
  @Override
  public IScopeContext getSaved(String scopeContextId) {
    return idScopeContextMap.get(scopeContextId);
  }
  
  @Override
  public Map<String, IScopeContext> getSaved() {
    return idScopeContextMap;
  }
  
  @Override
  public void close(IScope scope) {
    List<IScopeContext> scopeContexts = createdScopeContexts.remove(scope);
    if (scopeContexts != null) {
      scopeContexts.forEach(sc -> sc.internal().close());
    }
  }
  
  @Override
  public IStoreInternal internal() {
    return this;
  }
}
