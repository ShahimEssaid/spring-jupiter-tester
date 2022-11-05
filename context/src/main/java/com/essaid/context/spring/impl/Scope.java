package com.essaid.context.spring.impl;

import com.essaid.context.spring.IApplicationDomain;
import com.essaid.context.spring.IContext;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IScopeContext;
import com.essaid.context.spring.IThreadContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
public class Scope implements IScope {
  
  private final String scopeName;
  private final ConfigurableApplicationContext applicationContext;
  private final int order;
  private final IApplicationDomain domain;
  private final boolean threadInheritable;
  private final IScope parent;
  private final List<IScope> children = new ArrayList<>();
  private final AtomicLong sequence = new AtomicLong();
  
  public Scope(String scopeName, ConfigurableApplicationContext applicationContext, int order,
      IApplicationDomain domain, boolean threadInheritable, IScope parent) {
    if (scopeName.equals(IFactory.APPLICATION_NAME)) {
      if (parent != null) {
        throw new IllegalArgumentException(
            "Can't create scope: " + IFactory.APPLICATION_NAME + " with a non null parent: " + parent);
      }
    } else if (scopeName.equals(IFactory.CONTAINER_NAME)) {
      if (!parent.getScopeName().equals(IFactory.APPLICATION_NAME)) {
        throw new IllegalArgumentException(
            "Parent scope: " + parent + " has to be of name: " + IFactory.APPLICATION_NAME + " when creating scope: " + scopeName);
      }
    } else if (scopeName.equals(IFactory.SESSION_NAME)) {
      if (!parent.getScopeName().equals(IFactory.CONTAINER_NAME)) {
        throw new IllegalArgumentException(
            "Parent scope: " + parent + " has to be of name: " + IFactory.CONTAINER_NAME + " when creating scope: " + scopeName);
      }
    } else if (scopeName.equals(IFactory.CONVERSATION_NAME)) {
      if (!parent.getScopeName().equals(IFactory.SESSION_NAME)) {
        throw new IllegalArgumentException(
            "Parent scope: " + parent + " has to be of name: " + IFactory.SESSION_NAME + " when creating scope: " + scopeName);
      }
    } else if (scopeName.equals(IFactory.REQUEST_NAME)) {
      if (!parent.getScopeName().equals(IFactory.SESSION_NAME)) {
        throw new IllegalArgumentException(
            "Parent scope: " + parent + " has to be of name: " + IFactory.SESSION_NAME + " when creating scope: " + scopeName);
      }
    }
    
    this.scopeName = scopeName;
    this.applicationContext = applicationContext;
    this.order = order;
    this.domain = domain;
    this.threadInheritable = threadInheritable;
    this.parent = parent;
    if (parent != null) {
      parent.addChild(this);
    }
  }
  
  @Override
  public void addChild(IScope scope) {
    if (scopeName.equals(IFactory.APPLICATION_NAME) && !scope.getScopeName().equals(IFactory.CONTAINER_NAME)) {
      throw new IllegalArgumentException(
          scope.getScopeName() + " can't be added as a child scope of scope: " + scopeName);
    }
    children.add(scope);
  }
  
  @Override
  public void close() {
    domain.getStore().close(this);
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
  
  private IScopeContext getScopeContext() {
    IThreadContext threadContext = domain.getThreadManager().getThreadContext(domain.isAutoThreadContext());
    if (threadContext != null) {
      IContext iContext = threadContext.peekContext(domain.isAutoContext());
      if (iContext != null) {
        return iContext.getScopeContext(this, domain.isAutoScopeContext());
      }
    }
    return null;
  }
}
