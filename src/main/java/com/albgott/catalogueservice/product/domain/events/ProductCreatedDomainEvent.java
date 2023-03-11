package com.albgott.catalogueservice.product.domain.events;

import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.shared.domain.event.DomainEvent;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
public class ProductCreatedDomainEvent extends DomainEvent {
    private String code;
    private String name;
    private String businessId;

    public ProductCreatedDomainEvent() {
    }

    public ProductCreatedDomainEvent(Product product) {
        super(product.id());
        this.code = product.code();
        this.name = product.name();
        this.businessId = product.businessId();
    }

    public ProductCreatedDomainEvent(String aggregateId, String eventId, String occurredOn, String code, String name,
                                     String businessId) {
        super(aggregateId, eventId, occurredOn);
        this.code = code;
        this.name = name;
        this.businessId = businessId;
    }

    @Override
    public String eventName() {
        return "product.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>(){{
            put("code",code);
            put("name",name);
            put("businessId", businessId);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId,
                                      HashMap<String, Serializable> body,
                                      String eventId,
                                      String occurredOn) {
        return new ProductCreatedDomainEvent(
                aggregateId,eventId,occurredOn,
                (String) body.get("code"),
                (String) body.get("name"),
                (String) body.get("businessId")
        );
    }
}
