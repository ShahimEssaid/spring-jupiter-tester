package com.essaid.util.model.beanhandlers;

import com.essaid.util.model.IModel;

import java.util.Map;

public interface IBeanPropertyProxy extends IModel {
  default Map<Object, Object> getBeansData() {
    return internal().getData(IBeanPropertyProxy.class);
  }
}
