package com.essaid.util.intercept.data;

import com.essaid.util.model.beanhandlers.IBeanPropertyProxy;
import com.essaid.util.model.impl.Model;

public class Data extends Model implements IData, IBeanPropertyProxy {
  public Data(boolean permissive) {
    super(permissive);
  }
  
  public Data() {
  }
}
