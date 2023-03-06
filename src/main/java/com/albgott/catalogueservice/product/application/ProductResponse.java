package com.albgott.catalogueservice.product.application;

import com.albgott.catalogueservice.product.domain.model.Product;

import java.util.Set;

public class ProductResponse {
    private String business_id;
    private String id;
    private String code;
    private String name;
    private String description;
    private Set<String> images_ids;

    public ProductResponse(Product product) {
        this.business_id = product.businessId();
        this.id = product.id();
        this.code = product.code();
        this.name = product.name();
        this.description = product.description();
        this.images_ids = product.imagesIds();
    }
}
