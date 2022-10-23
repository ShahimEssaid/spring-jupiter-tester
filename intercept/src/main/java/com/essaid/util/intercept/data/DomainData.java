package com.essaid.util.intercept.data;


public class DomainData extends Data implements IDomainData {
  
  @Override
  public <T extends IDomainData> T as(Class<T> domainDataInterface) {
    return (T) super.doAs(domainDataInterface);
  }
}
