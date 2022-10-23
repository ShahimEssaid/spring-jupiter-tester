package com.essaid.util.intercept.tmp;

public interface ISubGenericInterface<T extends Number> extends ISuperGenericInterface<T> {
  T getValue();
}
