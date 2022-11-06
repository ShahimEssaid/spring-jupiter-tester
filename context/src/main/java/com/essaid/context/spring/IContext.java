package com.essaid.context.spring;

import java.util.Map;

public interface IContext {
  IScopeContext getScopeContext(IScope scope, boolean create);
  
  
  void addScopeContexts(boolean overwrite, IScopeContext... scopeContexts);
  
  IScopeContext removeScopeContext(IScope scope);
  
  Map<IScope, String> getRequestedScopeContextIds();
}
