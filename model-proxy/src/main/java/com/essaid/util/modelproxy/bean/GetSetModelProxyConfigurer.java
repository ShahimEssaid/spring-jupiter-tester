package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.impl.ModelProxyConfigurer;

public class GetSetModelProxyConfigurer<P extends IBeanProxy<P>> extends ModelProxyConfigurer<P> {
  
  public GetSetModelProxyConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{},
        new Class[]{GetSetHandler.class}, IBeanProxy.class);
    
  }
}
