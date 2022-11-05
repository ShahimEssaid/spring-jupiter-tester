package com.essaid.context.spring2;

import org.springframework.beans.factory.config.Scope;

public interface IScope extends Scope  {
  
  String getScopeName();
  
  int getOrder();
  
  void addChild(IScope scope);

  void close();
}