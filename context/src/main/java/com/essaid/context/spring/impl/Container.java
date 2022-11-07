package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IThreadManager;
import lombok.Getter;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Container implements IContainer {
  
  @Getter
  private final IDomain domain;
  @Getter
  private final ConfigurableApplicationContext springContext;
  
  @Getter
  private final IConfig config;
  private final Map<String, IScope> applicationScopes = new ConcurrentHashMap<>();
  @Getter
  private final IThreadManager threadManager;
  @Getter
  private volatile boolean initialized;
  private volatile IFactory factory;
  @Getter
  private volatile boolean closed;
  
  public Container(IDomain domain, ConfigurableApplicationContext springContext, IThreadManager threadManager, IConfig config) {
    if (springContext.isActive()) {
      throw new IllegalStateException(
          "Spring application context already active while creating IApplicationContext:" + this);
    }
    this.domain = domain;
    this.springContext = springContext;
    springContext.addApplicationListener(this);
    this.threadManager = threadManager;
    this.config = config;
  }
  
  @Override
  public IScope getScope(String scopeName) {
    return applicationScopes.get(scopeName);
  }
  
  @Override
  public IScope createScope(String scopeName, int order, IScope parent, boolean autoCreateScopeContext,
      boolean threadInheritable, IScope... relatedScopes) {
    checkIsNotInitialized();
    synchronized (applicationScopes) {
      IScope scope = getScope(scopeName);
      if (scope != null) {
        throw new IllegalStateException(
            "Scope already exists: " + scope + " when trying to create new scope with name: " + scopeName + " order:" + order + " parnet: " + parent + " autoCreateScopeContext: " + autoCreateScopeContext + " threadInheritable: " + threadInheritable);
      }
      scope = getFactory().createScope(this, scopeName, order, parent, config, relatedScopes);
      applicationScopes.put(scope.getScopeName(), scope);
      return scope;
    }
  }
  
  @Override
  public IFactory getFactory() {
    if (factory == null) return domain.getFactory();
    return factory;
  }
  
  
  @Override
  public void onApplicationEvent(SpringApplicationEvent event) {
  
  }
  
  private void checkIsInitialized() {
    if (!initialized) {
      throw new IllegalStateException("IApplicationContext not initialized yet: " + this);
    }
  }
  
  private void checkIsNotInitialized() {
    if (initialized) {
      throw new IllegalStateException("IApplicationContext already initialized: " + this);
    }
  }
}
