package com.essaid.util.model;

public interface IInterfaceable {
  <I extends IModelInterface> I as(Class<I> cls);
}
