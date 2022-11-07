package com.essaid.context.spring;

public interface IThreadContextList {
  
  
  IThreadContextListInternal internal();
  
  interface IThreadContextListInternal extends IThreadContextList {
    
    IThreadContext peekContext();
    
    IThreadContext popContext();
    
    void pushContext(IThreadContext context);
    
    boolean isEmpty();
    
    int size();
    
    
  }
}
