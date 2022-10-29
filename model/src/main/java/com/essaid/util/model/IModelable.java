package com.essaid.util.model;

public interface IModelable {
  <I extends IModelInterface> I modelableAs(Class<I> cls);
  
  IModel getModelableModel();
}
