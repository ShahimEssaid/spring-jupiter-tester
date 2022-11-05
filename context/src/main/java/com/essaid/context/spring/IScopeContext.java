package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  IScope getScope();
  
  void close();
}
