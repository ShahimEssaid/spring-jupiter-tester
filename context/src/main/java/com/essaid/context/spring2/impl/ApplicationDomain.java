package com.essaid.context.spring2.impl;

import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IFactory;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IScopeContext;
import com.essaid.context.spring2.IStore;
import com.essaid.context.spring2.IThreadContext;
import com.essaid.context.spring2.IThreadManager;
import com.essaid.context.spring2.Scopes;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.NamedThreadLocal;

public class ApplicationDomain implements IApplicationDomain {
  
  private static final Logger logger = LoggerFactory.getLogger(ApplicationDomain.class);
  
  @Getter
  private final String scopeName;
  @Getter
  private final boolean autoThreadContext;
  @Getter
  private final boolean autoScopeContext;
  @Getter
  private final boolean threadInheritable;

  @Getter
  private final boolean autoContext;
  private final String name;
  private IFactory factory;
  private IStore store;
  private IThreadManager threadManager;
  private IScopeContext applicationScopeContext;
  @Getter
  private boolean initialized;
  private IScope applicationScope;
  
  public ApplicationDomain(String domainName, boolean autoCreateThreadContext, boolean autoContext,
      boolean autoCreateScopeData, boolean inheritableApplicationScope) {
    this.name = domainName;
    this.scopeName = IFactory.APPLICATION_NAME;
    this.autoThreadContext = autoCreateThreadContext;
    this.autoContext = autoContext;
    this.autoScopeContext = autoCreateScopeData;
    this.threadInheritable = inheritableApplicationScope;
    
  }
  
  @Override
  public void initialize() {
    
    if (!initialized) {
      if (factory == null) {
        factory = new Factory(this);
      }
      if (store == null) {
        store = factory.createStore();
      }
      if(threadManager == null){
       threadManager =  factory.createThreadManager();
      }
      applicationScopeContext = factory.createScopeContext(this);
      this.applicationScope = Scopes.createApplicationScope(this, null);
      this.initialized = true;
    } else {
      logger.warn("Application domain: {} already initialized.", this);
    }
  }
  
  public IFactory getFactory() {
    return factory;
  }
  
  public void setFactory(IFactory factory) {
    if (this.factory != null) {
      throw new IllegalStateException(
          "Can't reset factory: " + this.factory + " with factory: " + factory + " and application domain: " + this);
    }
    Asserts.notNull(factory, "Can't set null factory on application domain: ", this);
    this.factory = factory;
  }
  
  @Override
  public IStore getStore() {
    return store;
  }
  
  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    return applicationScopeContext.get(name, objectFactory);
  }
  
  @Override
  public Object remove(String name) {
    return applicationScopeContext.remove(name);
  }
  
  @Override
  public void registerDestructionCallback(String name, Runnable callback) {
    applicationScopeContext.registerDestructionCallback(name, callback);
  }
  
  @Override
  public Object resolveContextualObject(String key) {
    return applicationScopeContext.resolveContextualObject(key);
  }
  
  @Override
  public String getConversationId() {
    return applicationScopeContext.getConversationId();
  }
  
  @Override
  public IScope getScope() {
    return null;
  }
  
  @Override
  public void close() {
    applicationScopeContext.close();
  }



  
  @Override
  public int getOrder() {
    return IFactory.APPLICATION_ORDER;
  }
  
  @Override
  public void addChild(IScope scope) {
    applicationScope.addChild(scope);
  }
  
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {
    if (ContextClosedEvent.class.isAssignableFrom(event.getClass())) {
      getFactory().close((ConfigurableApplicationContext) event.getApplicationContext());
    }
  }
  
  @Override
  public IThreadManager getThreadManager() {
    return threadManager;
  }
}
