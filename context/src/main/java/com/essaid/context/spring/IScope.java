package com.essaid.context.spring;

import org.springframework.beans.factory.config.Scope;

public interface IScope extends Scope  {
  
  String getScopeName();
  
  int getOrder();
  
  void addChild(IScope scope);

  void close();
}