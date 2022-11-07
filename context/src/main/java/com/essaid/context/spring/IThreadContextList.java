package com.essaid.context.spring;

public interface IThreadContextList {
  
  IThreadContext peekContext();
  
  IThreadContext popContext();
  
  void pushContext(IThreadContext context);
  
  boolean isEmpty();
  
  int size();
}
