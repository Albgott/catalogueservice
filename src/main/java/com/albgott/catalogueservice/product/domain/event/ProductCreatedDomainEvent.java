package com.albgott.catalogueservice.product.domain.event;

import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class ProductCreatedDomainEvent extends DomainEvent {
    private String businessId;
    private String productName;
    private String[] categoriesIds = {};

    public ProductCreatedDomainEvent() {
    }

    public ProductCreatedDomainEvent(Product product) {
        super(product.id().toString());
        this.businessId = product.businessId().toString();
        this.productName = product.name();
        this.categoriesIds = product.getCategoriesIds();
    }

    public ProductCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, String businessId,
                                     String productName, String[] categoriesIds) {
        super(aggregateId, eventId, occurredOn);
        this.businessId = businessId;
        this.productName = productName;
        this.categoriesIds = categoriesIds;
    }

    @Override
    public String eventName() {
        return "product.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("business_id",businessId);
            put("product_name",productName);
            put("categories_ids", categoriesIds);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new ProductCreatedDomainEvent(
                aggregateId, eventId, occurredOn,
                (String) body.get("business_id"),
                (String) body.get("product_name"),
                (String[]) body.get("categories_ids")
        );
    }
}
