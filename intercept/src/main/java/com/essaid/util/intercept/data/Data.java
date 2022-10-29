package com.essaid.util.intercept.data;

import com.essaid.util.model.beanhandlers.BeanPropertyConfigurer;
import com.essaid.util.model.defaulthandler.DefaultMethodConfigurer;
import com.essaid.util.model.impl.Model;

public class Data extends Model implements IData {
  public Data(boolean permissive) {
    super(permissive);
    appendConfiguration(new DefaultMethodConfigurer(), new BeanPropertyConfigurer());
  }
  
  public Data() {
    this(false);
  }
}
