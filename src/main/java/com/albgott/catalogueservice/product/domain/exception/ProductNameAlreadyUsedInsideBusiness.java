package com.albgott.catalogueservice.product.domain.exception;

import com.albgott.catalogueservice.shared.domain.exception.IllegalArgumentException;

public class ProductNameAlreadyUsedInsideBusiness extends IllegalArgumentException {
    public ProductNameAlreadyUsedInsideBusiness(String businessId, String name) {
        super("PRODUCT_NAME_ALREADY_ON_USE", String.format("There is already a product with name <%s> inside " +
                "business with id <%s>.",name,businessId));
    }
}
