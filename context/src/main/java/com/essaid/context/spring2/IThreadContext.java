package com.essaid.context.spring2;

public interface IThreadContext {
  
  IContext peekContext(boolean autoContext);
  
  IContext popContext();
  
  void pushContext(IContext context);
  
  boolean isEmpty();
}
