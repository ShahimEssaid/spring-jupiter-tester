package com.essaid.context.spring;

import com.essaid.util.model.beanhandlers.BeanPropertyConfigurer;
import com.essaid.util.model.defaulthandler.DefaultMethodConfigurer;
import com.essaid.util.model.impl.Model;

public class SpringScopeDataModel extends Model implements ISpringScopeDataModel {
  public SpringScopeDataModel(boolean permissive) {
    super(permissive);
    appendConfiguration(new DefaultMethodConfigurer(), new BeanPropertyConfigurer());
  }
  
  public SpringScopeDataModel() {
    this(false);
  }
}
