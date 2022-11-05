package com.essaid.context.spring;

import java.util.List;
import java.util.Map;

public interface IStore {
  
  void created(IScopeContext context);
  
  List<IScopeContext> getCreated(IScope scope);
  
  Map<IScope, List<IScopeContext>> getCreated();
  
  IScopeContext save(IScopeContext context, boolean overwrite);
  
  IScopeContext getSaved(String scopeContextId);
  
  Map<String, IScopeContext> getSaved();
  
  void close(IScope scope);
}
