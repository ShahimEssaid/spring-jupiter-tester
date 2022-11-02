package com.essaid.context.spring;

import com.essaid.util.model.IModelInterface;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.NamedThreadLocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

class SpringScopeDomain implements ISpringScopeDomain {
  
  private final ThreadLocal<ISpringThreadContext> contextHolder = new NamedThreadLocal<>("Spring " + "context");
//  private final ThreadLocal<ISpringContext> inheritableContextHolder = new NamedThreadLocal<>("Spring " +
//      "inheritable context");
  
  private final ISpringScopeData applicationScopeData;
  private final boolean inheritableApplicationScope;
  private final String domainName;
  private final Map<ConfigurableApplicationContext, Map<String, ISpringScope>> scopes = new HashMap<>();
  private final boolean permissiveScopeDataModel;
  Set<ISpringThreadContext> contexts = new HashSet<>();
  private boolean autoCreateScopeData = true;
  private boolean autoCreateContext = true;
  
  SpringScopeDomain(String domainName, boolean autoCreateContext, boolean autoCreateScopeData,
                    boolean inheritableApplicationScope,boolean permissiveScopeDataModel) {
    this.domainName = domainName;
    this.autoCreateContext = autoCreateContext;
    this.autoCreateScopeData = autoCreateScopeData;
    this.inheritableApplicationScope = inheritableApplicationScope;
    this.applicationScopeData = createScopeData(this, null, null);
    this.permissiveScopeDataModel = permissiveScopeDataModel;
  }
  
  public String getDomainName() {
    return domainName;
  }
  
  @Override
  public ISpringThreadContext resetThreadContext() {
    ISpringThreadContext currentContext = contextHolder.get();
//    if (currentContext == null) {
//      currentContext = inheritableContextHolder.get();
//    }
    contextHolder.remove();
//    inheritableContextHolder.remove();
    return currentContext;
  }
  
  @Override
  public ISpringThreadContext getThreadContext() {
    ISpringThreadContext context = contextHolder.get();
//    if (context == null) {
//      context = inheritableContextHolder.get();
//    }
    if (context == null) {
      if (isAutoCreateContext()) {
        context = createContext(Thread.currentThread());
        setThreadContext(context);
      }
    }
    return context;
  }
  
  @Override
  public ISpringThreadContext setThreadContext(ISpringThreadContext context) {
    
    ISpringThreadContext currentContext = contextHolder.get();
//    if (currentContext == null) {
//      currentContext = inheritableContextHolder.get();
//    }
    if (context == null) {
      return resetThreadContext();
    } else {
      contextHolder.set(context);
//      if (inheritable) {
//        contextHolder.remove();
//        inheritableContextHolder.set(context);
//      } else {
//        contextHolder.set(context);
//        inheritableContextHolder.remove();
//      }
    }
    return currentContext;
  }
  
  @Override
  public boolean isAutoCreateScopeData() {
    return autoCreateScopeData;
  }
  
  @Override
  public boolean isAutoCreateContext() {
    return autoCreateContext;
  }
  
  @Override
  public boolean isPermissiveScopeDataModel() {
    return permissiveScopeDataModel;
  }
  
  @Override
  public ISpringScope createScope(String scopeName, int order, ConfigurableApplicationContext applicationContext,
                                  Thread thread, boolean threadInheritable) {
    Map<String, ISpringScope> stringISpringScopeMap = scopes.get(applicationContext);
    if (stringISpringScopeMap != null) {
      ISpringScope iSpringScope = stringISpringScopeMap.get(scopeName);
      if (iSpringScope != null) {
        throw new IllegalStateException("Context domain: " + this + " asked to create duplicate scope named: " + scopeName + " for application context: " + applicationContext);
      }
    }
    
    SpringScope springScope = new SpringScope(scopeName, order, applicationContext, this, threadInheritable);
    Map<String, ISpringScope> stringISpringScopeMap1 = scopes.computeIfAbsent(applicationContext,
        applicationContext1 -> new HashMap<>());
    stringISpringScopeMap1.put(springScope.getScopeName(), springScope);
    
    return springScope;
  }
  
  @Override
  public ISpringScopeData createScopeData(ISpringScope scope, ISpringThreadContext context, Thread thread) {
    return new SpringScopeData(scope);
  }
  
  @Override
  public ISpringThreadContext createContext(Thread thread) {
    SpringThreadContext springThreadContext = new SpringThreadContext();
    springThreadContext.setScopeData(this, this);
    contexts.add(springThreadContext);
    return springThreadContext;
  }
  
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {
    if (ContextClosedEvent.class.isAssignableFrom(event.getClass())) {
      ConfigurableApplicationContext applicationContext =
          (ConfigurableApplicationContext) event.getApplicationContext();
      Set<ISpringScope> scopesToClear = new HashSet<>();
      for (ISpringThreadContext context : contexts) {
        scopesToClear.addAll(context.getApplicationContextScopes(applicationContext));
      }
      
      List<ISpringScope> scopesList = new ArrayList<>(scopesToClear);
      scopesList.sort(new Comparator<ISpringScope>() {
        @Override
        public int compare(ISpringScope o1, ISpringScope o2) {
          return o1.getScopeOrder() - o2.getScopeOrder();
        }
      });
      
      Collections.reverse(scopesList);
      
      for (ISpringScope scope : scopesList) {
        for (ISpringThreadContext context : contexts) {
          ISpringScopeData iSpringScopeData = context.removeScopeData(scope);
          if (iSpringScopeData != null) {
            iSpringScopeData.close();
          }
        }
      }
      this.scopes.remove(applicationContext);
    }
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
    return applicationScopeData;
  }
  
  @Override
  public ISpringScopeDomain getScopeDomain() {
    return this;
  }
  
  @Override
  public boolean isScopeThreadInheritable() {
    return this.inheritableApplicationScope;
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
  
  @Override
  public <I extends IModelInterface> I as(Class<I> cls) {
    return applicationScopeData.as(cls);
  }
  
  @Override
  public IModelInternal internal() {
    return applicationScopeData.internal();
  }
  
  @Override
  public Lock readLock() {
    return applicationScopeData.readLock();
  }
  
  @Override
  public Lock writeLock() {
    return applicationScopeData.writeLock();
  }

}
