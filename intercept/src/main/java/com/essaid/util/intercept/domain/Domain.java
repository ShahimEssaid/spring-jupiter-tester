package com.essaid.util.intercept.domain;

import com.essaid.util.intercept.data.DomainData;
import com.essaid.util.intercept.data.IDomainData;

public class Domain implements IDomain {
  
  
  private final IDomainData domainData;
  
  public Domain(IDomainData domainData) {
    this.domainData = domainData;
  }
  
  public Domain() {
    this.domainData = new DomainData();
  }
  
  public IDomainData getData() {
    return domainData;
  }
}
