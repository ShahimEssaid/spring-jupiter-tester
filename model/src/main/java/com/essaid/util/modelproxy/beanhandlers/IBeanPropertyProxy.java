package com.essaid.util.modelproxy.beanhandlers;

import com.essaid.util.modelproxy.IModel;

import java.util.Map;

public interface IBeanPropertyProxy extends IModel {
  default Map<Object, Object> getBeansData() {
    return internal().getData(IBeanPropertyProxy.class);
  }
}
