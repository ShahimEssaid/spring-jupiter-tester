package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IThreadContext;
import com.essaid.context.spring.IThreadContextList;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Container implements IContainer {
  
  @Getter
  private final IDomain domain;
  @Getter
  private final ConfigurableApplicationContext springContext;
  
  @Getter
  private final IConfig config;
  private final Map<String, IScope> containerScopes = new ConcurrentHashMap<>();
  
  private final IScope containScope;
  @Getter
  private volatile boolean initialized;
  private volatile IFactory factory;
  @Getter
  private volatile boolean closed;
  
  
  public Container(IDomain domain, ConfigurableApplicationContext springContext, IConfig config) {
    if (springContext.isActive()) {
      throw new IllegalStateException(
          "Spring application context already active while creating IApplicationContext:" + this);
    }
    this.domain = domain;
    this.springContext = springContext;
    springContext.addApplicationListener(this);
    IScope applicationScope = domain.getApplicationScope();
    springContext.getBeanFactory().registerScope(applicationScope.getScopeName(), applicationScope);
    
    this.config = config;
    containScope = domain.getFactory().createContainerScope(domain, domain.getApplicationScope(), config);
  }
  
  @Override
  public IScope getContainerScope() {
    return containScope;
  }
  
  @Override
  public IScope getScope(String scopeName) {
    return containerScopes.get(scopeName);
  }
  
  @Override
  public IScope createScope(String scopeName, int order, IScope parent, IScope... relatedScopes) {
    checkIsNotInitialized();
    synchronized (containerScopes) {
      IScope scope = getScope(scopeName);
      if (scope != null) {
        throw new IllegalStateException(
            "Scope already exists: " + scope + " when trying to create new scope with name: " + scopeName + " order:" + order + " parent: " + parent + " config: " + config);
      }
      scope = domain.getFactory().createScope(domain, scopeName, order, parent, config, relatedScopes);
      containerScopes.put(scope.getScopeName(), scope);
      springContext.getBeanFactory().registerScope(scope.getScopeName(), scope);
      return scope;
    }
  }
  
  
  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (ContextClosedEvent.class.isAssignableFrom(event.getClass())) {
      List<IScope> scopes = new ArrayList<>(containerScopes.values());
      scopes.sort(new Comparator<IScope>() {
        @Override
        public int compare(IScope o1, IScope o2) {
          return o2.getScopeOrder() - o1.getScopeOrder();
        }
      });
      scopes.forEach(s -> s.close());
      domain.unregisterSpringContext(springContext);
    }
    
    
  }
  
  @Override
  public IThreadContext getThreadContex() {
    return domain.getContext(config);
  }
  
  @Override
  public IThreadContextList getThreadContextList() {
    return domain.getThreadContextList(config);
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
