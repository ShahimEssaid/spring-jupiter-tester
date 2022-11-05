package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  IScope getScope();
  
  void close();
  
  void setName(String name);
  
  String getScopeContextName();
  
  String getScopeContextId();
  
  Boolean isClosed();
  
  boolean isProperNamed();
  
  IScopeContext save(boolean overwrite);
}
