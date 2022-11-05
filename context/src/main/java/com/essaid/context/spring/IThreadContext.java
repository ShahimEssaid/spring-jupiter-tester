package com.essaid.context.spring;

public interface IThreadContext {
  
  IContext peekContext(boolean autoContext);
  
  IContext popContext();
  
  void pushContext(IContext context);
  
  boolean isEmpty();
}
