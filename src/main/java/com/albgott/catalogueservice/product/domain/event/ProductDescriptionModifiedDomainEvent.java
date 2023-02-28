package com.albgott.catalogueservice.product.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ProductDescriptionModifiedDomainEvent extends DomainEvent {
    private String description;

    public ProductDescriptionModifiedDomainEvent() {
    }

    public ProductDescriptionModifiedDomainEvent(String aggregateId, String description) {
        super(aggregateId);
        this.description = description;
    }

    public ProductDescriptionModifiedDomainEvent(String aggregateId, String eventId, String occurredOn, String description) {
        super(aggregateId, eventId, occurredOn);
        this.description = description;
    }

    @Override
    public String eventName() {
        return "product.description.modified";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("description", description);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new ProductDescriptionModifiedDomainEvent(
                aggregateId,eventId,occurredOn,
                (String) body.get("description")
        );
    }
}
