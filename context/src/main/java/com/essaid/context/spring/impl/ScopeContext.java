package com.essaid.context.spring.impl;

import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class ScopeContext implements IScopeContext {
  
  private static Logger logger = LoggerFactory.getLogger(ScopeContext.class);
  
  @Getter
  private final IScope scope;
  private final Map<String, Object> objectMap = new HashMap<>();
  private final Map<String, Runnable> destructorMap = new HashMap<>();
  private volatile boolean closed;
  
  private String name;
  private long timeout;
  private long latestTimestamp;
  
  public ScopeContext(IScope scope) {
    this.scope = scope;
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
    if (this.name != null) {
      throw new IllegalStateException("Can't rename to: " + name + " for scope context: " + this);
    }
    Asserts.notNull(name, "Can't name scope context with null name for context: ", this);
    this.name = name;
  }
  
  @Override
  public String getScopeContextName() {
    return name;
  }
  
  @Override
  public String getScopeContextId() {
    
    if (getScopeContextName() == null) {
      setName(scope.generateContextId());
    }
    
    String contextId = scope.getScopeName() + ":" + getScopeContextName();
    contextId = scope.getParent() == null ? contextId : scope.getParent()
        .getScopeContext(scope.getApplicationDomain().isAutoThreadContext(),
            scope.getApplicationDomain().isAutoContext(), scope.getApplicationDomain().isAutoScopeContext())
        .getScopeContextId() + "|" + contextId;
    return contextId;
  }
  
  @Override
  public Boolean isClosed() {
    return closed;
  }
  
  @Override
  public boolean isProperNamed() {
    return getScopeContextName() != null && !getScopeContextName().startsWith("_");
  }
  
  @Override
  public IScopeContext save(boolean overwrite) {
    return scope.getApplicationDomain().getStore().save(this, overwrite);
  }
}
