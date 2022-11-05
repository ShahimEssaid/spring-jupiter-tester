package com.essaid.context.spring2;

public interface IThreadManager {
  IThreadContext getThreadContext(boolean create);
  
  IThreadContext setThreadContext(IThreadContext context, boolean overwrite);
  
  IThreadContext removeThreadContext();
}
