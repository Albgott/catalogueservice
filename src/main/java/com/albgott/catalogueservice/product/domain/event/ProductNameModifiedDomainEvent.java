package com.albgott.catalogueservice.product.domain.event;

import com.albgott.catalogueservice.category.domain.event.CategoryNameModifiedDomainEvent;
import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ProductNameModifiedDomainEvent extends DomainEvent {

    private String name;

    public ProductNameModifiedDomainEvent() {
    }

    public ProductNameModifiedDomainEvent(String aggregateId, String name) {
        super(aggregateId);
        this.name = name;
    }

    public ProductNameModifiedDomainEvent(String aggregateId, String eventId, String occurredOn, String name) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
    }

    @Override
    public String eventName() {
        return "product.name.modified";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("name",name);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new CategoryNameModifiedDomainEvent(
                aggregateId,eventId,occurredOn,
                (String) body.get("name")
        );
    }
}
