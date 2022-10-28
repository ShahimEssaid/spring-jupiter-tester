package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.impl.ModelProxyConfigurer;

public class BeanPropertyProxyConfigurer extends ModelProxyConfigurer {
  
  public BeanPropertyProxyConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{},
        new Class[]{BeanPropertyHandler.class}, IBeanPropertyProxy.class);
    
  }
}
