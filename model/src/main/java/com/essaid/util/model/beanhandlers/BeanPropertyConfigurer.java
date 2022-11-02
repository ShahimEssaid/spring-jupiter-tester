package com.essaid.util.model.beanhandlers;

import com.essaid.util.model.impl.ModelConfigurer;

public class BeanPropertyConfigurer extends ModelConfigurer {
  
  public BeanPropertyConfigurer() {
    super("id", "name", "version", 0, "shortDescription", "description", "notes", new Class[]{},
        new Class[]{BeanPropertyHandler.class}, IBeanPropertyModel.class);
    
  }
}
