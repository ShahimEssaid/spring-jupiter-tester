package com.essaid.util.modelproxy;

public interface IInterfaceable {
  <I extends IModelInterface> I as(Class<I> cls);
}
