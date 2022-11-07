package com.essaid.context.spring;

public interface IThreadManager {
  
  
  IThreadContextList getThreadContextList(IDomain domain, IConfig config);
  
  IThreadContextList setThreadContextList(IThreadContextList context, boolean overwrite);
  
  IThreadContext getThreadContext(IDomain domain, IConfig config);
  
  IThreadContextList removeThreadContextList();
}
