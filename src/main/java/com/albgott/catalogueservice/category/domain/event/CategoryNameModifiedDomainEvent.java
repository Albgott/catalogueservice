package com.albgott.catalogueservice.category.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class CategoryNameModifiedDomainEvent extends DomainEvent {
    private String name;
    public CategoryNameModifiedDomainEvent() {
    }

    public CategoryNameModifiedDomainEvent(String aggregateId, String name) {
        super(aggregateId);
        this.name = name;
    }

    public CategoryNameModifiedDomainEvent(String aggregateId, String eventId, String occurredOn, String name) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
    }

    @Override
    public String eventName() {
        return "category.name.modified";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("category_name",name);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new CategoryNameModifiedDomainEvent(
                aggregateId, eventId, occurredOn,
                (String) body.get("category_name")
        );
    }
}
