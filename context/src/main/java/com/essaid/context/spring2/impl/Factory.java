package com.essaid.context.spring2.impl;

import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IContext;
import com.essaid.context.spring2.IFactory;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IScopeContext;
import com.essaid.context.spring2.IThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Factory implements IFactory {
  
  private static Logger logger = LoggerFactory.getLogger(Factory.class);
  private final IApplicationDomain domain;
  
  private Map<ConfigurableApplicationContext, Map<String, Scope>> scopes = new HashMap<>();
  
  public Factory(IApplicationDomain domain){
    this.domain = domain;
  }
  
  @Override
  public Scope createScope(String scopeName, ConfigurableApplicationContext applicationContext, int order,
      IApplicationDomain domain, boolean threadInheritable, IScope parent) {
    Map<String, Scope> scopesByName = scopes.computeIfAbsent(applicationContext, c -> new HashMap<>());
    Scope scope = scopesByName.get(scopeName);
    if (scope != null) {
      logger.warn("Scope {} already exists when creating duplicate scope.", scope);
    } else {
      scope = new Scope(scopeName, applicationContext, order, domain, threadInheritable, parent);
      scopesByName.put(scopeName, scope);
    }
    return scope;
  }
  
  @Override
  public IScopeContext createScopeContext(IScope scope) {
    return new ScopeContext(scope);
  }
  
  @Override
  public IThreadContext createThreadContext() {
    
    return new ThreadContext(domain);
  }
  
  @Override
  public IContext createContext() {
    return new Context(domain);
  }
}
