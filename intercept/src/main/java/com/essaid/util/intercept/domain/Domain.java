package com.essaid.util.intercept.domain;

import com.essaid.util.intercept.data.DomainData;
import com.essaid.util.intercept.data.IDomainData;

public class Domain implements   IDomain{
  
  IDomainData domainData = new DomainData();
  
  @Override
  public IDomainData getData() {
    return domainData;
  }
}
