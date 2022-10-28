package com.essaid.util.modelproxy;

public interface IProxiable {
  <I extends IModelInterface> I as(Class<I> cls);
}
