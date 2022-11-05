package com.essaid.context.spring;

public interface IThreadManager {
  IThreadContext getThreadContext(boolean create);
  
  IThreadContext setThreadContext(IThreadContext context, boolean overwrite);
  
  IThreadContext removeThreadContext();
  
  void enterContext(IContext context);
  
  IContext exitContext();
}
