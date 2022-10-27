package com.essaid.util.domainproxy;

import com.essaid.util.modelproxy.IModelProxy;

public interface IDomainProxy extends IModelProxy<IDomainProxy> {
    DomainInterfaceDescriptions getInterfaceDescriptions();
}
