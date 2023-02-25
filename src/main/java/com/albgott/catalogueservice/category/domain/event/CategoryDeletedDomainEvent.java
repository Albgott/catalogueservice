package com.albgott.catalogueservice.category.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class CategoryDeletedDomainEvent extends DomainEvent {
    public CategoryDeletedDomainEvent() {
    }

    public CategoryDeletedDomainEvent(String aggregateId) {
        super(aggregateId);
    }

    public CategoryDeletedDomainEvent(String aggregateId, String eventId, String occurredOn) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "category.deleted";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new CategoryDeletedDomainEvent(aggregateId,eventId,occurredOn);
    }
}
