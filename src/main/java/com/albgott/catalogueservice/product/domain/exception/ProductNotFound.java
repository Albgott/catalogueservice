package com.albgott.catalogueservice.product.domain.exception;

import com.albgott.catalogueservice.shared.domain.exception.NotFoundExeption;

public class ProductNotFound extends NotFoundExeption {
    public ProductNotFound(String productId) {
        super("PRODUCT_NOT_FOUND", String.format("Product with id <%s> not found.",productId));;
    }
}
