package com.essaid.context.spring;

public interface IStore {
  
  void created(IScopeContext context);
  void close(IScope scope);
}
