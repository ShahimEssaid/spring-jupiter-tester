package com.essaid.context.spring;

public interface IContext {
  IScopeContext getScopeContext(IScope scope, boolean create);
  
  IScopeContext setScopeContext(IScope scope, IScopeContext context, boolean overwrite);
  
  IScopeContext removeScopeContext(IScope scope);
}
