package com.essaid.context.spring;

import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Set;

public interface ISpringContext {
  
  ISpringScopeData getScopeData(ISpringScope scope);
  
  ISpringScopeData setScopeData(ISpringScope scope, ISpringScopeData data);
  
  ISpringScopeData removeScopeData(ISpringScope scope);
  
  /**
   * This is a list because a ISpringContext can hold multiple {@link ISpringScopeData} for a scope name, one per
   * {@link org.springframework.context.ApplicationContext} if there are multiple. Each ISpringScopeData will have its
   * own {@link ISpringScope} which in turn holds the ApplicationContext.  The ISpringScope for the "application" scope
   * name has null for the application context.
   *
   * @param scopeName
   * @return
   */
  List<ISpringScopeData> getScopeData(String scopeName);
  
  ISpringScopeData getScopeData(String scopeName, ConfigurableApplicationContext applicationContext);
  
  
  ISpringScopeData getScopeData(ISpringScope scope, boolean create);
  
  
  Set<ISpringScopeData> getApplicationContextData(ConfigurableApplicationContext applicationContext);
  
  Set<ISpringScope> getApplicationContextScopes(ConfigurableApplicationContext applicationContext);
  
  
  boolean isEmpty();
}
