package com.essaid.util.domainproxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DomainInterfaceDescriptions {

    final private static Logger logger = LoggerFactory.getLogger(DomainInterfaceDescriptions.class);

    private ServiceLoader<IDomainInterfaceDescription> serviceLoader;

    public DomainInterfaceDescriptions() {
        this.serviceLoader = ServiceLoader.load(IDomainInterfaceDescription.class);
    }

    public DomainInterfaceDescriptions(ServiceLoader<IDomainInterfaceDescription> serviceLoader) {
        this.serviceLoader = serviceLoader;
    }

    public void loadDomainDescriptions() {
        Iterator<IDomainInterfaceDescription> iterator = serviceLoader.iterator();
        List<IDomainInterfaceDescription> descriptions = new ArrayList<>();

        while (iterator.hasNext()) {
            descriptions.add(iterator.next());
        }

        // we add from lowest priority to override with higher
        Collections.sort(descriptions, (o1, o2) -> o2.priority() - o1.priority());

        descriptions.forEach(description -> addActiveDescription(null));
    }

    private Map<Class<? extends IDomainProxy>, IDomainInterfaceDescription> activeDescriptions = new ConcurrentHashMap<>();

    public Map<Class<? extends IDomainProxy>, IDomainInterfaceDescription> getActiveDescriptions() {
        return activeDescriptions;
    }

    public Map<Class<? extends IDomainProxy>, IDomainInterfaceDescription> getIncactiveDescriptions() {
        return incactiveDescriptions;
    }

    private Map<Class<? extends IDomainProxy>, IDomainInterfaceDescription> incactiveDescriptions = new ConcurrentHashMap<>();

    public IDomainInterfaceDescription addActiveDescription(IDomainInterfaceDescription description) {
        logger.debug("Adding active IDomainInterfaceDescription: " + description);
        IDomainInterfaceDescription removed = incactiveDescriptions.remove(description.getInterface());
        if (removed != null) {
            logger.debug("\tRemoved incative:" + removed);
        }
        removed = activeDescriptions.put(description.getInterface(), description);
        if (removed != null) {
            logger.debug("\tRemoved previous active: " + removed);
        }
        return removed;
    }

    public IDomainInterfaceDescription addInactiveDescription(IDomainInterfaceDescription description) {
        logger.debug("Adding inactive IDomainInterfaceDescription: " + description);
        IDomainInterfaceDescription removed = activeDescriptions.remove(description.getInterface());
        if (removed != null) {
            logger.debug("\tRemoved ative:" + removed);
        }
        removed = incactiveDescriptions.put(description.getInterface(), description);
        if (removed != null) {
            logger.debug("\tRemoved previous inactive: " + removed);
        }

        return removed;
    }

}
