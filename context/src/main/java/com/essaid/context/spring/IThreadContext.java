package com.essaid.context.spring;

import java.util.Map;

public interface IThreadContext {
  
  IThreadContextInternal internal();
  
  interface IThreadContextInternal extends IThreadContext {
    IScopeContext getScopeContext(IScope scope, IConfig config);
    
    
    void addScopeContexts(boolean overwrite, IConfig config, IScopeContext... scopeContexts);
    
    IScopeContext removeScopeContext(IScope scope);
    
    Map<IScope, String> getRequestedScopeContextIds();
    
    IDomain getDomain();
    
    
  }
}
