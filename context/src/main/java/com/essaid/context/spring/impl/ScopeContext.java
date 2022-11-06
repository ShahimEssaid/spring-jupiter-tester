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
  
  private static final Logger logger = LoggerFactory.getLogger(ScopeContext.class);
  
  @Getter
  private final IScope scope;
  private final Map<String, Object> objectMap = new HashMap<>();
  private final Map<String, Runnable> destructorMap = new HashMap<>();
  private volatile boolean closed;
  
  private String savedId;
  private String scopeContextName;
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
  
  @Override
  public String getScopeContextName() {
    return scopeContextName;
  }
  
  public void setScopeContextName(String scopeContextName) {
    if (isProperNamed()) {
      throw new IllegalStateException("Can't rename to: " + scopeContextName + " for scope context: " + this);
    }
    Asserts.notNull(scopeContextName, "Can't name scope context with null name for context: ", this);
    this.scopeContextName = scopeContextName;
  }
  
  @Override
  public String getScopeContextId() {
    
    if (getScopeContextName() == null) {
      this.setScopeContextName(scope.generateContextId());
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
  
  @Override
  public boolean isSaved() {
    return savedId != null;
  }
  
  @Override
  public String getSavedId() {
    return savedId;
  }
  
  @Override
  public void setSavedId(String savedId) {
    if (this.savedId != null && !this.savedId.equals(savedId)) {
      throw new IllegalStateException(
          "Context scope: " + this + " is being saved with a new id while already assigned one. Current ID:" + this.savedId + " and new id:" + savedId);
    }
    Asserts.notNull(savedId, "Null scope context save id.");
    this.savedId = savedId;
    
  }
  
  @Override
  public boolean isNamed() {
    return getScopeContextName() != null;
  }
}
