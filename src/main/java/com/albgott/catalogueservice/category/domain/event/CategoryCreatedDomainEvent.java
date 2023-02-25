package com.albgott.catalogueservice.category.domain.event;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class CategoryCreatedDomainEvent extends DomainEvent {
    private String businessId;
    private String name;

    public CategoryCreatedDomainEvent() {
    }

    public CategoryCreatedDomainEvent(Category category) {
        super(category.id().toString());
        this.businessId = category.businessId().toString();
        this.name = category.name();
    }

    public CategoryCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, String businessId,
                                      String name) {
        super(aggregateId, eventId, occurredOn);
        this.businessId = businessId;
        this.name = name;
    }

    @Override
    public String eventName() {
        return "category.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("business_id",businessId);
            put("category_name",name);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new CategoryCreatedDomainEvent(
                aggregateId,eventId,occurredOn,
                (String) body.get("business_id"),
                (String) body.get("category_name")
        );
    }
}
