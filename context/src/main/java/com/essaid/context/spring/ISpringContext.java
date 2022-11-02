package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Set;

public interface ISpringContext {
  
  ISpringScopeData getScopeData(ISpringScope scope);
  
  ISpringScopeData setScopeData(ISpringScope scope, ISpringScopeData data);
  
  ISpringScopeData removeScopeData(ISpringScope scope);
  
  List<ISpringScopeData> getScopeData(String scopeName);
  
  ISpringScopeData getScopeData(String scopeName, ConfigurableApplicationContext applicationContext);
  
  
  ISpringScopeData getScopeData(ISpringScope scope, boolean create);
  
  
  Set<ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext);
  
  Set<ISpringScope> getApplicationContextScopes(ConfigurableApplicationContext applicationContext);
  
  
  boolean isEmpty();
}
