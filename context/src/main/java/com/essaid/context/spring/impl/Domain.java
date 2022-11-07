package com.essaid.context.spring.impl;

import com.essaid.context.spring.IConfig;
import com.essaid.context.spring.IContainer;
import com.essaid.context.spring.IDomain;
import com.essaid.context.spring.IFactory;
import com.essaid.context.spring.IScope;
import com.essaid.context.spring.IThreadManager;
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
  
  @Getter
  private final IScope applicationScope;
  @Getter
  private final IConfig config;
  
  private final Map<ConfigurableApplicationContext, IContainer> containersMap = new ConcurrentHashMap<>();
  @Getter
  private final IThreadManager threadManager;
  //private final Map<ConfigurableApplicationContext, IContainer> closedApplicationContextMap = new ConcurrentHashMap<>();
  
  @Getter
  private boolean initialized;
  private Thread shutdownHook;
  
  
  public Domain(String domainName, IFactory factory, IThreadManager threadManager, IConfig config) {
    Asserts.allNotNull("Null argument(s) when constructing IContextDomain", domainName, factory);
    this.domainName = domainName;
    this.factory = factory;
//    this.threadManager = threadManager;
    this.applicationScope = factory.createApplicationScope(this, null, config);
    
    this.config = config;
    this.threadManager = threadManager;
  }
  
  @Override
  public IContainer registerSpringContext(ConfigurableApplicationContext context, IConfig config) {
    synchronized (containersMap) {
      IContainer applicationContext = containersMap.get(context);
      if (applicationContext != null)
        throw new IllegalStateException("ConfigurableApplicationContext already registered:" + context);
      applicationContext = getFactory().createApplicationContext(this, context, config != null ? config : this.config);
      containersMap.put(context, applicationContext);
      return applicationContext;
    }
  }
  
  @Override
  public IContainer unregisterSpringContext(ConfigurableApplicationContext context) {
    IContainer removed = containersMap.remove(context);
    return removed;
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
  
  @Override
  public void registerShutdownHook() {
    
    if (this.shutdownHook == null) {
      this.shutdownHook = new Thread(new Runnable() {
        @Override
        public void run() {
          
          // close just in case. it doesn't hurt to do this right away based on the spring implementation.
          if (config.isCloseSpringContextIfNeeded()) {
            containersMap.keySet().forEach(applicationContext -> applicationContext.close());
          }
          
          // now wait until the contexts are not active, up to the timeout
          // this waiting is needed in case the contexts are shutting down with their own
          // shutdown hooks and we need to give them some reasonable time to finish.
          long timeout = System.currentTimeMillis() + config.getCloseSpringContextDelay();
          
          stillActive:
          while (true) {
            if (System.currentTimeMillis() > timeout) {
              logger.warn(
                  "Waiting for containers to be inactive in shutdown hook timed out after: {} milliseconds with containers: {}",
                  config.getCloseSpringContextDelay(), containersMap);
              break;
            }
            
            for (Map.Entry<ConfigurableApplicationContext, IContainer> containerEntry : containersMap.entrySet()) {
              if (containerEntry.getKey().isActive()) {
                try {
                  Thread.sleep(100);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
                logger.debug("Waiting on active spring context: {}", containerEntry.getValue());
                continue stillActive;
              }
            }
            // they are all not active at this point.
            break;
          }
          
          // they are all inactive, or not much more to do about it.
          
          Domain.this.closeDomain();
        }
      });
      
      Runtime.getRuntime().addShutdownHook(shutdownHook);
      
    }
    
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
