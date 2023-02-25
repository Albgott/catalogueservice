package com.albgott.catalogueservice.category.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class CategoryDescriptionModifiedDomainEvent extends DomainEvent {

    private String description;

    public CategoryDescriptionModifiedDomainEvent() {
    }

    public CategoryDescriptionModifiedDomainEvent(String aggregateId, String description) {
        super(aggregateId);
        this.description = description;
    }

    public CategoryDescriptionModifiedDomainEvent(String aggregateId, String eventId, String occurredOn, String description) {
        super(aggregateId, eventId, occurredOn);
        this.description = description;
    }

    @Override
    public String eventName() {
        return "category.description.modified";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("category_description",description);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new CategoryDescriptionModifiedDomainEvent(
                aggregateId, eventId, occurredOn,
                (String) body.get("category_description")
        );
    }
}
