package com.albgott.catalogueservice.product.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ProductDeletedDomainEvent extends DomainEvent {

    public ProductDeletedDomainEvent() {
    }

    public ProductDeletedDomainEvent(String aggregateId) {
        super(aggregateId);
    }

    public ProductDeletedDomainEvent(String aggregateId, String eventId, String occurredOn) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "product.deleted";
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
        return new ProductDeletedDomainEvent(aggregateId, eventId, occurredOn);
    }
}
