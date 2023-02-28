package com.albgott.catalogueservice.product.domain.event;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ProductRemovedFromCategoriesDomainEvent extends DomainEvent {

    private String[] categoriesIds = {};

    public ProductRemovedFromCategoriesDomainEvent() {
    }

    public ProductRemovedFromCategoriesDomainEvent(String aggregateId, String[] categoriesIds) {
        super(aggregateId);
        this.categoriesIds = categoriesIds;
    }

    public ProductRemovedFromCategoriesDomainEvent(String aggregateId, String eventId, String occurredOn,
                                                   String[] categoriesIds) {
        super(aggregateId, eventId, occurredOn);
        this.categoriesIds = categoriesIds;
    }

    @Override
    public String eventName() {
        return "product.categories.removed";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("categories_ids",categoriesIds);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new ProductRemovedFromCategoriesDomainEvent(
                aggregateId,eventId,occurredOn,
                (String[]) body.get("categories_ids")
        );
    }
}
