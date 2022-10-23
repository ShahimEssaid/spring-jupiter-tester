package com.essaid.util.intercept.data;

public interface IDomainData {
  <T extends IDomainData> T as(Class<T> domainDataInterface);
}
