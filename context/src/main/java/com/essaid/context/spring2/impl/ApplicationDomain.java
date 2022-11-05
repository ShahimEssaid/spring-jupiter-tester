package com.essaid.context.spring2.impl;

import com.essaid.context.spring2.IApplicationDomain;
import com.essaid.context.spring2.IFactory;
import com.essaid.context.spring2.IScope;
import com.essaid.context.spring2.IScopeContext;
import com.essaid.context.spring2.IThreadContext;
import com.essaid.context.spring2.Scopes;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.core.NamedThreadLocal;

public class ApplicationDomain implements IApplicationDomain{
  
  private static final Logger logger = LoggerFactory.getLogger(ApplicationDomain.class);
  
  @Getter
  private final String scopeName;
  @Getter
  private final boolean autoThreadContext;
  @Getter
  private final boolean autoScopeContext;
  @Getter
  private final boolean threadInheritable;
  private final NamedThreadLocal<IThreadContext> threadContextHolder = new NamedThreadLocal<>("Spring thread context");
  @Getter
  private final boolean autoContext;
  private final String name;
  private IFactory factory;
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
  public void close() {
    applicationScopeContext.close();
  }
  
  @Override
  public IThreadContext getThreadContext(boolean create) {
    IThreadContext context = threadContextHolder.get();
    if (context == null && create) {
      context = getFactory().createThreadContext();
      threadContextHolder.set(context);
    }
    return context;
  }
  
  @Override
  public IThreadContext setThreadContext(IThreadContext context, boolean overwrite) {
    IThreadContext existingContext = getThreadContext(false);
    if (existingContext != null && !overwrite) {
      throw new IllegalStateException(
          "Can't overwrite thread context for application domain: " + this + ", current context: " + existingContext + ", and new context:" + context);
    }
    threadContextHolder.set(context);
    return existingContext;
  }
  
  @Override
  public IThreadContext removeThreadContext() {
    IThreadContext threadContext = getThreadContext(false);
    threadContextHolder.remove();
    return threadContext;
  }
  
  @Override
  public void addChild(IScope scope) {
    applicationScope.addChild(scope);
  }
  
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {
  
  }
}
