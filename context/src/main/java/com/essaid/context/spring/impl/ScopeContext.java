package com.essaid.context.spring.impl;

import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScopeContext implements IScopeContext {
  
  private static final Logger logger = LoggerFactory.getLogger(ScopeContext.class);
  
  @Getter
  private final IScope scope;
  @Getter
  private final String id;
  private final Map<String, Object> objectMap = new ConcurrentHashMap<>();
  private final Map<String, Runnable> destructorMap = new ConcurrentHashMap<>();
  @Getter
  private volatile String name;
  @Getter
  private volatile boolean closed;
  
  @Getter
  @Setter
  private volatile long timeout = Long.MAX_VALUE;
  private volatile long latestTimestamp = System.currentTimeMillis();
  
  public ScopeContext(IScope scope, String id) {
    this.scope = scope;
    this.id = id;
  }
  
  public void close() {
    if (closed) {
      logger.warn("Scope context already closed: {}", this);
      return;
    }
    objectMap.keySet().forEach(key -> {
      Runnable destructor = destructorMap.remove(key);
      if (destructor != null) {
        destructor.run();
      }
    });
    objectMap.clear();
    closed = true;
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    Object object = objectMap.get(name);
    if (object == null) {
      object = objectFactory.getObject();
      objectMap.put(name, object);
    }
    return object;
  }
  
  @Override
  public Object remove(String name) {
    destructorMap.remove(name);
    return objectMap.remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    destructorMap.put(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }
  
  @Override
  public String getConversationId() {
    return null;
  }
  
  
  public void setName(String name) {
    Asserts.notNull(name, "Can't name scope context with null name for context: ", this);
    if (this.name != null) {
      throw new IllegalStateException(
          "Can't rename: " + this.name + " to: " + name + " for scope context: " + this);
    }
    this.name = name;
  }
  
  @Override
  public boolean isNamed() {
    return getName() != null;
  }
}
