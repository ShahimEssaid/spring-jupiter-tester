package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScope extends Scope {
  
  String getScopeName();
  
  int getOrder();
  
  void addChild(IScope scope);
  
  void close();
  
  IScopeContext getScopeContext(boolean createThreadContext, boolean createContext, boolean createScopeContext);
  
  
  String generateContextId();
  
  IScope getParent();
  
  IApplicationDomain getApplicationDomain();
  
  Boolean isClosed();
}