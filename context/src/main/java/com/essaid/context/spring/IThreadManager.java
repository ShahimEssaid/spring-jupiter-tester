package com.essaid.context.spring;

public interface IThreadManager {
  
  
  IThreadContextList getThreadContextList(IContainer container,IConfig config);
  
  IThreadContextList setThreadContextList(IThreadContextList context, boolean overwrite);
  
  IThreadContext getContext(IContainer container, IConfig config);
  
  
  // ================ older
  
  
  IThreadContextList removeThreadContextList();
  
}
