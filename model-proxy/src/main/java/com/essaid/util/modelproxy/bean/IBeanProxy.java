package com.essaid.util.modelproxy.bean;

import com.essaid.util.modelproxy.IModelProxy;

import java.util.Map;

public interface IBeanProxy<P extends IBeanProxy<P>> extends IModelProxy<P> {
  
  default Map<Object, Object> getGetSetData() {
    return internal().getData(IBeanProxy.class);
  }
  
  
}
