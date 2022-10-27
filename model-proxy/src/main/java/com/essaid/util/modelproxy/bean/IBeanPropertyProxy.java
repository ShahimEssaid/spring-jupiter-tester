package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.IModelProxy;

import java.util.Map;

public interface IBeanPropertyProxy<P extends IBeanPropertyProxy<P>> extends IModelProxy<P> {
  
  default Map<Object, Object> getBeansData() {
    return internal().getData(IBeanPropertyProxy.class);
  }
  
  
}
