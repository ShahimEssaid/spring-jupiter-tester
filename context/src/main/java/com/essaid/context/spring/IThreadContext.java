package com.essaid.context.spring;

import java.util.Map;

public interface IThreadContext {
  IScopeContext getScopeContext(IScope scope, IConfig config);
  
  
  void addScopeContexts(boolean overwrite, IConfig config, IScopeContext... scopeContexts);
  
  IScopeContext removeScopeContext(IScope scope);
  
  Map<IScope, String> getRequestedScopeContextIds();
}
