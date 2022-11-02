package com.essaid.util.model.beanhandlers;

import com.essaid.util.model.IModel;

import java.util.Map;

public interface IBeanPropertyModel extends IModel {
  default Map<Object, Object> getBeansData() {
    return internal().getData(IBeanPropertyModel.class);
  }
}
