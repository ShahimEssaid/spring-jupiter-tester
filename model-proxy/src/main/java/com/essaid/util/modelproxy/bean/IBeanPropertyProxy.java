package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.IModelProxy;

import java.util.Map;

public interface IBeanPropertyProxy extends IModelProxy {
  
  default Map<Object, Object> getBeansData() {
    return internal().getData(IBeanPropertyProxy.class);
  }
  
  
}
