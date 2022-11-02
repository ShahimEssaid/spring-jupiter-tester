package com.essaid.context.spring;

import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class SpringScopeData implements ISpringScopeData {
  
  private final Map<String, Object> scopeObjects = new HashMap<>();
  private final Map<String, Runnable> scopeDestructors = new HashMap<>();
  private volatile boolean active;
  
  @Override
  public boolean isActive() {
    return active;
  }
  
  @Override
  public void setActive(boolean active) {
    this.active = active;
  }
  
  @Override
  public void close() {
    scopeObjects.keySet().forEach(key -> {
      Runnable destructor = scopeDestructors.remove(key);
      if(destructor!= null){
        destructor.run();
      }
    });
    scopeObjects.clear();
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    Object object = scopeObjects.get(name);
    if (object == null) {
      object = objectFactory.getObject();
      scopeObjects.put(name, object);
    }
    return object;
  }
  
  @Override
  public Object remove(String name) {
    checkActive();
    scopeDestructors.remove(name);
    return scopeObjects.remove(name);
  }
  
  private void checkActive() {
    if (!active) {
      throw new IllegalStateException();
    }
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    scopeDestructors.put(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }
  
  @Override
  public String getConversationId() {
    return null;
  }
}
