package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class SpringContext implements ISpringContext {
  
  private final Map<ConfigurableApplicationContext, Map<ISpringScope, ISpringScopeData>> scopes = new HashMap<>();
  
  @Override
  public ISpringScopeData getScopeData(ISpringScope scope) {
    return getScopeData(scope, SpringThreadManager.getDomain().isAutoCreateScopes());
  }
  
  @Override
  public ISpringScopeData getScopeData(ISpringScope scope, boolean create) {
    synchronized (scopes) {
      ConfigurableApplicationContext applicationContext = scope.getApplicationContext();
      Map<ISpringScope, ISpringScopeData> scopesData = scopes.get(applicationContext);
      if (scopesData == null) {
        scopesData = new HashMap<>();
        scopes.put(applicationContext, scopesData);
      }
      ISpringScopeData iSpringScopeData = scopesData.get(scope);
      if (iSpringScopeData == null && create) {
        iSpringScopeData = SpringThreadManager.getDomain().createScopeData(scope, this, Thread.currentThread());
        scopesData.put(scope, iSpringScopeData);
      }
      return iSpringScopeData;
    }
  }
  
  @Override
  public Map<ISpringScope, ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext) {
    return scopes.get(applicationContext);
  }
  
  @Override
  public void closeApplicationContext(ConfigurableApplicationContext applicationContext) {
    Map<ISpringScope, ISpringScopeData> removed = scopes.remove(applicationContext);
    if (removed != null) {
      removed.forEach((iSpringScope, iSpringScopeData) -> {
        iSpringScopeData.close();
      });
    }
  }
  
  @Override
  public boolean isEmpty() {
    return scopes.isEmpty();
  }
}
