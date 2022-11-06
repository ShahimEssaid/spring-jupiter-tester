package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  IScope getScope();
  
  void close();
  
  void setScopeContextName(String scopeContextName);
  
  String getScopeContextName();
  
  String getScopeContextId();
  
  Boolean isClosed();
  
  boolean isProperNamed();
  
  boolean isNamed();
  
  IScopeContext save(boolean overwrite);
  
  boolean isSaved();
  
  String getSavedId();
  void setSavedId(String savedId);
}
