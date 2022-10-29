package com.essaid.util.model;

public interface IModelable {
  <I extends IModelInterface> I as(Class<I> cls);
  
  IModel getModel();
}
