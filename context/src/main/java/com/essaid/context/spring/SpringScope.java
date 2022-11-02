package com.essaid.context.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringScope implements ISpringScope {
  
  private final int scopeId;
  private final String scopeName;
  private final ConfigurableApplicationContext applicationContext;
  
  public ConfigurableApplicationContext getApplicationContext() {
    return applicationContext;
  }
  
  public SpringScope(String scopeName, int scopeId, ConfigurableApplicationContext applicationContext) {
    this.scopeName = scopeName;
    this.scopeId = scopeId;
    this.applicationContext = applicationContext;
  }
  
  public int getScopeId() {
    return scopeId;
  }
  
  public String getScopeName() {
    return scopeName;
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    ISpringContext context = SpringThreadManager.getContext();
    ISpringScopeData scopeData = context.getScopeData(this);
    return scopeData.get(name, objectFactory);
  }
  
  @Override
  public Object remove(String name) {
    ISpringContext context = SpringThreadManager.getContext();
    ISpringScopeData scopeData = context.getScopeData(this);
    return scopeData.remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    ISpringContext context = SpringThreadManager.getContext();
    ISpringScopeData scopeData = context.getScopeData(this);
    scopeData.registerDestructionCallback(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    ISpringContext context = SpringThreadManager.getContext();
    ISpringScopeData scopeData = context.getScopeData(this);
    return scopeData.resolveContextualObject(key);
  }
  
  @Override
  public String getConversationId() {
    ISpringContext context = SpringThreadManager.getContext();
    ISpringScopeData scopeData = context.getScopeData(this);
    return scopeData.getConversationId();
  }
  
  @Override
  public String getName() {
    return scopeName;
  }
  
  @Override
  public int getId() {
    return scopeId;
  }
}
