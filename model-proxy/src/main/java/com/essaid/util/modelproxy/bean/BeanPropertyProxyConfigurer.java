package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.impl.ModelProxyConfigurer;

public class BeanPropertyProxyConfigurer<P extends IBeanPropertyProxy<P>> extends ModelProxyConfigurer<P> {
  
  public BeanPropertyProxyConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{},
        new Class[]{BeanPropertyHandler.class}, IBeanPropertyProxy.class);
    
  }
}
