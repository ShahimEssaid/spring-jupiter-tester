package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SpringContext implements ISpringContext {
  
  //private final Map<String, Map<ConfigurableApplicationContext, ISpringScopeData>> scopes2 = new HashMap<>();
  private final Map<ISpringScope, ISpringScopeData> scopes = new HashMap<>();
  
  @Override
  public ISpringScopeData getScopeData(ISpringScope scope) {
    return getScopeData(scope, SpringThreadManager.getDomain().isAutoCreateScopes());
  }
  
  @Override
  public ISpringScopeData setScopeData(ISpringScope scope, ISpringScopeData data) {
    return scopes.put(scope, data);
  }
  
  @Override
  public ISpringScopeData removeScopeData(ISpringScope scope) {
    return scopes.remove(scope);
  }
  
  @Override
  public List<ISpringScopeData> getScopeData(String scopeName) {
    List<ISpringScopeData> data = new ArrayList<>();
    
    return scopes.entrySet()
        .stream()
        .filter(entry -> entry.getKey().getScopeName().equals(scopeName))
        .map(entry -> entry.getValue())
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
        iSpringScopeData = SpringThreadManager.getDomain().createScopeData(scope, this, Thread.currentThread());
        scopes.put(scope, iSpringScopeData);
      }
      return iSpringScopeData;
    }
  }
  
  
  @Override
  public Set<ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext) {
    Set<ISpringScope> contextScopes = getApplicationContextScopes(applicationContext);
    return  contextScopes.stream().map(scopes::get).collect(Collectors.toSet());
  }
  
  @Override
  public Set<ISpringScope> getApplicationContextScopes(ConfigurableApplicationContext applicationContext) {
    return scopes.keySet()
        .stream()
        .filter(scope -> applicationContext.equals(scope.getScopeApplicationContext()))
        .collect(Collectors.toSet());
  }
//
//  @Override
//  public void closeApplicationContext(ConfigurableApplicationContext applicationContext) {
//    Iterator<Map.Entry<ISpringScope, ISpringScopeData>> iterator1 = scopes.entrySet().iterator();
//    while (iterator1.hasNext()){
//      Map.Entry<ISpringScope, ISpringScopeData> next = iterator1.next();
//      if(next.getKey().getScopeApplicationContext().equals(applicationContext)){
//        iterator1.remove();
//        next.getValue().close();
//      }
//    }
//
//    Set<String> scopeNames = new HashSet<>(scopes2.keySet());
//    for (String scopeName : scopeNames) {
//      Map<ConfigurableApplicationContext, ISpringScopeData> contextsData = scopes2.get(scopeName);
//      Iterator<Map.Entry<ConfigurableApplicationContext, ISpringScopeData>> iterator = contextsData.entrySet()
//          .iterator();
//      while (iterator.hasNext()) {
//        Map.Entry<ConfigurableApplicationContext, ISpringScopeData> dataEntry = iterator.next();
//        if (dataEntry.getKey().equals(applicationContext)) {
//          iterator.remove();
//          dataEntry.getValue().close();
//        }
//      }
//      if (contextsData.isEmpty()) {
//        scopes2.remove(scopeName);
//      }
//    }
//  }
  
  @Override
  public boolean isEmpty() {
    return scopes.isEmpty();
  }
}
