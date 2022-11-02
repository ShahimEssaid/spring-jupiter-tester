package com.essaid.context.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class SpringContextDomain implements ISpringContextDomain {
  
  private boolean autoScopes = true;
  private boolean autoContexts = true;
  Set<ISpringContext> contexts = new HashSet<>();
  
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
  public ISpringScope createScope(String scopeName, int scopeId, ConfigurableApplicationContext applicationContext,
                                  Thread thread) {
    return new SpringScope(scopeName, scopeId, applicationContext);
  }
  
  @Override
  public ISpringScopeData createScopeData(ISpringScope scope, ISpringContext context, Thread thread) {
    return new SpringScopeData();
  }
  
  @Override
  public ISpringContext createContext(Thread thread) {
    SpringContext springContext = new SpringContext();
    contexts.add(springContext);
    return springContext;
  }
  
  @Override
  public void onApplicationEvent(ApplicationContextEvent event) {
    if(ContextClosedEvent.class.isAssignableFrom(event.getClass())){
      ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) event.getApplicationContext();
      for (ISpringContext context : contexts){
        context.closeApplicationContext(applicationContext);
      }
      contexts.removeIf(ISpringContext::isEmpty);
    }
    
    System.out.println("==== application even:" + event);
  }
}
