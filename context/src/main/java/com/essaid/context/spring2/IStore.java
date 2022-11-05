package com.essaid.context.spring2;

public interface IStore {
  
  void created(IScopeContext context);
  void close(IScope scope);
}
