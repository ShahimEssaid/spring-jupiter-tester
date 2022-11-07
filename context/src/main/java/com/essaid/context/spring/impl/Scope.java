package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import lombok.Getter;
import org.springframework.beans.factory.ObjectFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Scope implements IScope {
  
  @Getter
  private final String scopeName;
  @Getter
  private final int scopeOrder;
  @Getter
  private final IScope parentScope;
  @Getter
  private final List<IScope> childScopes = new ArrayList<>();
  @Getter
  private final List<IScope> relatedScopes = new ArrayList<>();
  
  private final AtomicLong sequence = new AtomicLong();
  @Getter
  private final IConfig config;
  private final Map<String, IScopeContext> contexts = new ConcurrentHashMap<>();
  
  @Getter
  private final IDomain domain;
  @Getter
  private volatile boolean closed;

//  public Scope(String scopeName, ConfigurableApplicationContext applicationContext, int order,
//      IContextDomain applicationDomain, boolean threadInheritable, IScope parent) {
//    if (scopeName.equals(IApplicationContext.APPLICATION_NAME)) {
//      if (parent != null) {
//        throw new IllegalArgumentException(
//            "Can't create scope: " + IApplicationContext.APPLICATION_NAME + " with a non null parent: " + parent);
//      }
//    } else if (scopeName.equals(IApplicationContext.CONTAINER_NAME)) {
//      if (!parent.getScopeName().equals(IApplicationContext.APPLICATION_NAME)) {
//        throw new IllegalArgumentException(
//            "Parent scope: " + parent + " has to be of name: " + IApplicationContext.APPLICATION_NAME + " when creating scope: " + scopeName);
//      }
//    } else if (scopeName.equals(IApplicationContext.SESSION_NAME)) {
//      if (!parent.getScopeName().equals(IApplicationContext.CONTAINER_NAME)) {
//        throw new IllegalArgumentException(
//            "Parent scope: " + parent + " has to be of name: " + IApplicationContext.CONTAINER_NAME + " when creating scope: " + scopeName);
//      }
//    } else if (scopeName.equals(IApplicationContext.CONVERSATION_NAME)) {
//      if (!parent.getScopeName().equals(IApplicationContext.SESSION_NAME)) {
//        throw new IllegalArgumentException(
//            "Parent scope: " + parent + " has to be of name: " + IApplicationContext.SESSION_NAME + " when creating scope: " + scopeName);
//      }
//    } else if (scopeName.equals(IApplicationContext.REQUEST_NAME)) {
//      if (!parent.getScopeName().equals(IApplicationContext.SESSION_NAME)) {
//        throw new IllegalArgumentException(
//            "Parent scope: " + parent + " has to be of name: " + IApplicationContext.SESSION_NAME + " when creating scope: " + scopeName);
//      }
//    }
//
//    this.scopeName = scopeName;
//    this.applicationContext = applicationContext;
//    this.order = order;
//    this.applicationDomain = applicationDomain;
//    this.threadInheritable = threadInheritable;
//    this.parent = parent;
//    if (parent != null) {
//      parent.addChildScope(this);
//    }
//  }
//
  
  public Scope(IDomain domain, String scopeName, int scopeOrder, IScope parentScope, IConfig config,
      IScope... relatedScopes) {
    //this.container = container;
    this.domain = domain;
    this.scopeName = scopeName;
    this.scopeOrder = scopeOrder;
    this.parentScope = parentScope;
    this.config = config;
    
    for (IScope scope : relatedScopes) {
      addRelatedScope(scope);
    }
  }
  
  @Override
  public boolean addChildScope(IScope scope) {
    synchronized (childScopes) {
      if (childScopes.contains(scope)) return false;
      return childScopes.add(scope);
    }
  }
  
  @Override
  public boolean removeChildScope(IScope scope) {
    synchronized (childScopes) {
      return childScopes.remove(scope);
    }
  }
  
  @Override
  public boolean addRelatedScope(IScope related) {
    synchronized (relatedScopes) {
      if (relatedScopes.contains(related)) return false;
      return relatedScopes.add(related);
    }
  }
  
  @Override
  
  public boolean removeRelatedScope(IScope related) {
    synchronized (relatedScopes) {
      return relatedScopes.remove(related);
    }
  }
  
  @Override
  public String generateScopeContextId() {
    return Long.toString(sequence.getAndIncrement());
  }
  
  @Override
  public void scopeContextCreated(IScopeContext context) {
    synchronized (contexts) {
      contexts.put(context.getId(), context);
    }
  }
  
  @Override
  public IScopeContext getScopeContext() {
    return domain.getContext(config).getScopeContext(this, config);
  }
  
  // ==================================   older
  
  @Override
  public boolean close() {
    
    if (closed) return false;
    
    contexts.values().forEach(sc -> sc.close());
    closed = true;
    return true;
  }
  
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    IScopeContext scopeContext = getScopeContext();
    return scopeContext == null ? null : scopeContext.get(name, objectFactory);
  }
  
  @Override
  public Object remove(String name) {
    IScopeContext scopeContext = getScopeContext();
    return scopeContext == null ? null : scopeContext.remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    IScopeContext scopeContext = getScopeContext();
    if (scopeContext != null) {
      scopeContext.registerDestructionCallback(name, callback);
    } else {
      throw new IllegalStateException(
          "Attempting to register destruction callback but no scope context present for bean name: " + name + " and scope: " + this);
    }
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    IScopeContext scopeContext = getScopeContext();
    return scopeContext == null ? null : scopeContext.resolveContextualObject(key);
  }
  
  @Override
  public String getConversationId() {
    IScopeContext scopeContext = getScopeContext();
    return scopeContext == null ? null : scopeContext.getConversationId();
  }
  
  @Override
  public String toString() {
    return "Scope[name:" + scopeName + ", parent:" + parentScope + "]";
    
  }
}
