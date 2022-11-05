package com.essaid.context.spring2.impl;

import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IContext;
import com.essaid.context.spring2.IFactory;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IScopeContext;
import com.essaid.context.spring2.IStore;
import com.essaid.context.spring2.IThreadContext;
import com.essaid.context.spring2.IThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factory implements IFactory {
  
  private static Logger logger = LoggerFactory.getLogger(Factory.class);
  private final IApplicationDomain domain;
  
  private Map<ConfigurableApplicationContext, Map<String, Scope>> scopes = new HashMap<>();
  
  public Factory(IApplicationDomain domain) {
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
    ScopeContext scopeContext = new ScopeContext(scope);
    domain.getStore().created(scopeContext);
    return scopeContext;
  }
  
  @Override
  public IThreadContext createThreadContext() {
    
    return new ThreadContext(domain);
  }
  
  @Override
  public IContext createContext() {
    return new Context(domain);
  }
  
  @Override
  public IStore createStore() {
    return new Store(domain);
  }
  
  
  @Override
  public void close(ConfigurableApplicationContext context) {
    Map<String, Scope> remove = scopes.remove(context);
    if (remove != null) {
      List<IScope> contextscopes = new ArrayList<>(remove.values());
      Collections.sort(contextscopes, new Comparator<IScope>() {
        
        @Override
        public int compare(IScope o1, IScope o2) {
          return o2.getOrder() - o1.getOrder();
        }
      });
      contextscopes.forEach(cs -> cs.close());
    }
    
  }
  
  @Override
  public IThreadManager createThreadManager() {
    return new ThreadManager(domain);
  }
}
