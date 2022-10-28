package com.essaid.util.modelproxy.beanhandlers;

import com.essaid.util.modelproxy.impl.ModelConfigurer;

public class BeanPropertyConfigurer extends ModelConfigurer {
  
  public BeanPropertyConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{},
        new Class[]{BeanPropertyHandler.class}, IBeanPropertyProxy.class);
    
  }
}
