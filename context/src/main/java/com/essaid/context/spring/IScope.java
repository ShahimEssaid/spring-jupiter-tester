package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

import java.util.Collection;

public interface IScope extends Scope {
  
  IScopeInternal internal();
  interface IScopeInternal extends  IScope{
  IDomain getDomain();
  
  // scope related
  String getScopeName();
  
  int getScopeOrder();
  
  IScope getParentScope();
  
  boolean addChildScope(IScope scope);
  
  boolean removeChildScope(IScope scope);
  
  Collection<IScope> getChildScopes();
  
  boolean addRelatedScope(IScope related);
  
  boolean removeRelatedScope(IScope related);
  
  Collection<IScope> getRelatedScopes();
  
  String generateScopeContextId();
  
  
  void scopeContextCreated(IScopeContext context);
  
  IScopeContext getScopeContext();
  
  boolean isClosed();
  
  IConfig getConfig();
  
  boolean close();
  

  
  }
}