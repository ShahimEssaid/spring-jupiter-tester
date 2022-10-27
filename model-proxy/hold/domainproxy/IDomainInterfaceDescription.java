package com.essaid.util.domainproxy;

public interface IDomainInterfaceDescription {

    String id();

    String name();

    String version();

    int priority();

    String shortDescription();

    String description();

    String notes();

    <T extends IDomainProxy> Class<T> getInterface();
    
}
