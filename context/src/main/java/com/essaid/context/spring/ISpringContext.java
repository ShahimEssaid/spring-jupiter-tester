package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

public interface ISpringContext {
  
  ISpringScopeData getScopeData(ISpringScope scope);
  
  ISpringScopeData getScopeData(ISpringScope scope, boolean create);
  
  Map<ISpringScope, ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext);
  
  void closeApplicationContext(ConfigurableApplicationContext applicationContext);
  
  boolean isEmpty();
}
