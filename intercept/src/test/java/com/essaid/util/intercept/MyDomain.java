package com.essaid.util.intercept;


import com.essaid.util.intercept.domain.Domain;
import com.essaid.util.intercept.domain.IDomain;

public class MyDomain extends Domain {
  
  
  public MyDomain(IDomain domainProxy) {
    super(domainProxy);
  }
}
