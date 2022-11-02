package com.essaid.context.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpringContextDomain implements ISpringContextDomain {
  
  private final ISpringScopeData applicationScopeData;
  Set<ISpringContext> contexts = new HashSet<>();
  private boolean autoScopes = true;
  private boolean autoContexts = true;
  private Map<ConfigurableApplicationContext, Map<String, ISpringScope>> scopes = new HashMap<>();
  
  public SpringContextDomain() {
    
    this.applicationScopeData = createScopeData(this, null, null);
  }
  
  public void setAutoCreateContext(boolean autoContexts) {
    this.autoContexts = autoContexts;
  }
  
  @Override
  public boolean isAutoCreateScopes() {
    return autoScopes;
  }
  
  public void setAutoCreateScopes(boolean autoScopes) {
    this.autoScopes = autoScopes;
  }
  
  @Override
  public boolean isAutoCreateContexts() {
    return autoContexts;
  }
  
  @Override
  public ISpringScope createScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                                  Thread thread) {
    Map<String, ISpringScope> stringISpringScopeMap = scopes.get(applicationContext);
    if (stringISpringScopeMap != null) {
      ISpringScope iSpringScope = stringISpringScopeMap.get(scopeName);
      if (iSpringScope != null) {
        throw new IllegalStateException("Context domain: " + this + " asked to create duplicate scope named: " + scopeName + " for application context: " + applicationContext);
      }
    }
    
    SpringScope springScope = new SpringScope(scopeName, order, applicationContext, this);
    Map<String, ISpringScope> stringISpringScopeMap1 = scopes.computeIfAbsent(applicationContext,
        applicationContext1 -> new HashMap<>());
    stringISpringScopeMap1.put(springScope.getScopeName(), springScope);
    
    return springScope;
  }
  
  @Override
  public ISpringScopeData createScopeData(ISpringScope scope, ISpringContext context, Thread thread) {
    return new SpringScopeData(scope);
  }
  
  @Override
  public ISpringContext createContext(Thread thread) {
    SpringContext springContext = new SpringContext();
    springContext.setScopeData(this, this);
    contexts.add(springContext);
    return springContext;
  }
  
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {
    if (ContextClosedEvent.class.isAssignableFrom(event.getClass())) {
      ConfigurableApplicationContext applicationContext =
          (ConfigurableApplicationContext) event.getApplicationContext();
      Set<ISpringScope> scopesToClear = new HashSet<>();
      for (ISpringContext context : contexts) {
        scopesToClear.addAll(context.getApplicationContextScopes(applicationContext));
      }
      
      List<ISpringScope> scopesList = new ArrayList<>(scopesToClear);
      Collections.sort(scopesList, new Comparator<ISpringScope>() {
        @Override
        public int compare(ISpringScope o1, ISpringScope o2) {
          return o1.getScopeOrder() - o2.getScopeOrder();
        }
      });
      
      Collections.reverse(scopesList);
      
      for (ISpringScope scope : scopesList) {
        for (ISpringContext context : contexts) {
          ISpringScopeData iSpringScopeData = context.removeScopeData(scope);
          if (iSpringScopeData != null) {
            iSpringScopeData.close();
          }
        }
      }
      this.scopes.remove(applicationContext);
    }
    System.out.println("==== application even:" + event);
  }
  
  
  @Override
  public String getScopeName() {
    return SpringScopes.APPLICATION_NAME;
  }
  
  @Override
  public int getScopeOrder() {
    return SpringScopes.APPLICATION_ORDER;
  }
  
  @Override
  public ConfigurableApplicationContext getScopeApplicationContext() {
    return null;
  }
  
  @Override
  public ISpringScopeData getScopeData() {
    return SpringThreadManager.getContext().getScopeData(this);
  }
  
  @Override
  public ISpringContextDomain getScopeDomain() {
    return this;
  }
  
  @Override
  public boolean isActive() {
    return applicationScopeData.isActive();
  }
  
  @Override
  public void setActive(boolean active) {
    applicationScopeData.setActive(active);
  }
  
  @Override
  public void close() {
    applicationScopeData.close();
  }
  
  @Override
  public ISpringScope getScope() {
    return this;
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return applicationScopeData.get(name, objectFactory);
  }
  
  @Override
  public Object remove(String name) {
    return applicationScopeData.remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    applicationScopeData.registerDestructionCallback(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    return applicationScopeData.resolveContextualObject(key);
  }
  
  @Override
  public String getConversationId() {
    return applicationScopeData.getConversationId();
  }
}
