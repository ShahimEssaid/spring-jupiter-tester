package com.essaid.context.spring2;

import org.springframework.beans.factory.config.Scope;

public interface IScopeContext extends Scope {
  
  void close();
}
