package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.util.asserts.Asserts;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Domain implements IDomain {
  
  private static final Logger logger = LoggerFactory.getLogger(Domain.class);
  
  @Getter
  private final String domainName;
  @Getter
  private final IFactory factory;
  //  @Getter
//  private final IThreadManager threadManager;
  @Getter
  private final IScope applicationScope;
  @Getter
  private final IConfig config;
  
  private final Map<ConfigurableApplicationContext, IContainer> applicationContextMap = new ConcurrentHashMap<>();
  
  @Getter
  private boolean initialized;
  
  public Domain(String domainName, IScope applicationScope, IFactory factory, IConfig config) {
    Asserts.allNotNull("Null argument(s) when constructing IContextDomain", domainName, factory, applicationScope);
    this.domainName = domainName;
    this.factory = factory;
//    this.threadManager = threadManager;
    this.applicationScope = applicationScope;
    
    this.config = config;
  }
  
  
  @Override
  public IContainer registerSpringContext(ConfigurableApplicationContext context) {
    synchronized (applicationContextMap) {
      IContainer applicationContext = applicationContextMap.get(context);
      if (applicationContext != null)
        throw new IllegalStateException("ConfigurableApplicationContext already registered:" + context);
      applicationContext = getFactory().createApplicationContext(this, context, applicationContext.getThreadManager(), config);
      applicationContextMap.put(context, applicationContext);
      return applicationContext;
    }
  }
  
  @Override
  public void initialize() {
    if (!initialized) {
      this.initialized = true;
    } else {
      logger.warn("Application domain: {} already initialized.", this);
    }
  }
  
  @Override
  public void closeDomain() {
    getApplicationScope().close();
  }
  
  
  private void checkIsInitialized() {
    if (!initialized) {
      throw new IllegalStateException("IContextDomain not initialized yet:" + this);
    }
  }
  
  private void checkIsNotInitialized() {
    if (initialized) {
      throw new IllegalStateException("IContextDomain already initialized:" + this);
    }
  }
}
