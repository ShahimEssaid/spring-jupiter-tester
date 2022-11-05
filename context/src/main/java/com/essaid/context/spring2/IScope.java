package com.essaid.context.spring2;

import org.springframework.beans.factory.config.Scope;

public interface IScope extends Scope  {
  
  String getScopeName();
  
  void addChild(IScope scope);
}