package com.albgott.catalogueservice.shared.domain.model;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents(){
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    final protected void record(DomainEvent event){
        domainEvents.add(event);
    }

}
