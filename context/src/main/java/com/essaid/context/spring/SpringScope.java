package com.essaid.context.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringScope implements ISpringScope {
  
  private final String scopeName;
  private final ConfigurableApplicationContext applicationContext;
  private final int order;
  
  public SpringScope(String scopeName, int order, ConfigurableApplicationContext applicationContext) {
    this.scopeName = scopeName;
    this.applicationContext = applicationContext;
    this.order = order;
  }
  
  public ConfigurableApplicationContext getScopeApplicationContext() {
    return applicationContext;
  }
  
  @Override
  public ISpringScopeData getScopeData() {
    return  SpringThreadManager.getContext().getScopeData(this);
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return getScopeData().get(name, objectFactory);
  }
  
  @Override
  public Object remove(String name) {
    return getScopeData().remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    getScopeData().registerDestructionCallback(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    return getScopeData().resolveContextualObject(key);
  }
  
  @Override
  public String getConversationId() {
    return getScopeData().getConversationId();
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
