package com.essaid.util.domainproxy;

import com.essaid.util.modelproxy.ModelProxy;

public class Domain extends ModelProxy<IDomainProxy> implements IDomainProxy {

    final private DomainInterfaceDescriptions descriptions;

    public Domain() {
        this.descriptions = new DomainInterfaceDescriptions();
    }

    public Domain(DomainInterfaceDescriptions descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public DomainInterfaceDescriptions getInterfaceDescriptions() {

        return descriptions;
    }

}
