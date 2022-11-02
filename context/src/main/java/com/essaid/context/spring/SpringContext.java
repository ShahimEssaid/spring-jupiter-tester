package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class SpringContext implements ISpringContext {
  
  //private final Map<String, Map<ConfigurableApplicationContext, ISpringScopeData>> scopes2 = new HashMap<>();
  private final Map<ISpringScope, ISpringScopeData> scopes = new HashMap<>();
  
  @Override
  public ISpringScopeData getScopeData(ISpringScope scope) {
    return getScopeData(scope, SpringContextUtil.getDomain().isAutoCreateScopeData());
  }
  
  @Override
  public ISpringScopeData setScopeData(ISpringScope scope, ISpringScopeData data) {
    assert scope == data.getScope() : "Adding scope data to context with a scope mismatch. assert scope == data" +
        ".getScope() " + "failed.";
    return scopes.put(scope, data);
  }
  
  @Override
  public ISpringScopeData removeScopeData(ISpringScope scope) {
    return scopes.remove(scope);
  }
  
  @Override
  public List<ISpringScopeData> getScopeData(String scopeName) {
    return scopes.entrySet()
        .stream()
        .filter(entry -> entry.getKey().getScopeName().equals(scopeName))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
  }
  
  @Override
  public ISpringScopeData getScopeData(String scopeName, ConfigurableApplicationContext applicationContext) {
    for (Map.Entry<ISpringScope, ISpringScopeData> entry : scopes.entrySet()) {
      ISpringScope scope = entry.getKey();
      if (scope.getScopeName().equals(scopeName) && scope.getScopeApplicationContext().equals(applicationContext)) {
        return entry.getValue();
      }
    }
    return null;
  }
  
  @Override
  public ISpringScopeData getScopeData(ISpringScope scope, boolean create) {
    synchronized (scopes) {
      ISpringScopeData iSpringScopeData = scopes.get(scope);
      if (iSpringScopeData == null && create) {
        iSpringScopeData = SpringContextUtil.getDomain().createScopeData(scope, this, Thread.currentThread());
        scopes.put(scope, iSpringScopeData);
      }
      return iSpringScopeData;
    }
  }
  
  
  @Override
  public Set<ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext) {
    Set<ISpringScope> contextScopes = getApplicationContextScopes(applicationContext);
    return contextScopes.stream().map(scopes::get).collect(Collectors.toSet());
  }
  
  @Override
  public Set<ISpringScope> getApplicationContextScopes(ConfigurableApplicationContext applicationContext) {
    return scopes.keySet()
        .stream()
        .filter(scope -> applicationContext.equals(scope.getScopeApplicationContext()))
        .collect(Collectors.toSet());
  }
  
  @Override
  public boolean isEmpty() {
    return scopes.isEmpty();
  }
}
