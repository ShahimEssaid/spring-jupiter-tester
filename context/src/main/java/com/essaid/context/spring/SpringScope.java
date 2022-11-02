package com.essaid.context.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringScope implements ISpringScope {
  
  private final String scopeName;
  private final ConfigurableApplicationContext applicationContext;
  private final int order;
  private final ISpringContextDomain domain;
  
  public boolean isScopeThreadInheritable() {
    return threadInheritable;
  }
  
  private final boolean threadInheritable;
  
  public SpringScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                     ISpringContextDomain domain, boolean threadInheritable) {
    this.scopeName = scopeName;
    this.applicationContext = applicationContext;
    this.order = order;
    this.domain = domain;
    this.threadInheritable = threadInheritable;
  }
  
  public ConfigurableApplicationContext getScopeApplicationContext() {
    return applicationContext;
  }
  
  @Override
  public ISpringScopeData getScopeData() {
    return domain.getScopeData(this);
  }
  
  @Override
  public ISpringContextDomain getScopeDomain() {
    return domain;
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    ISpringScopeData scopeData = getScopeData();
    if (scopeData != null) {
      return scopeData.get(name, objectFactory);
    }
    return null;
  }
  
  @Override
  public Object remove(String name) {
    ISpringScopeData scopeData = getScopeData();
    if (scopeData != null) {
      return scopeData.remove(name);
    }
    return null;
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    ISpringScopeData scopeData = getScopeData();
    if (scopeData != null) {
      scopeData.registerDestructionCallback(name, callback);
    }
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    ISpringScopeData scopeData = getScopeData();
    if (scopeData != null) {
      return  scopeData.resolveContextualObject(key);
    }
    return null;
  }
  
  @Override
  public String getConversationId() {
    ISpringScopeData scopeData = getScopeData();
    if (scopeData != null) {
      return  scopeData.getConversationId();
    }
    return null;
  }
  
  @Override
  public String getScopeName() {
    return scopeName;
  }
  
  @Override
  public int getScopeOrder() {
    return order;
  }
  
}
