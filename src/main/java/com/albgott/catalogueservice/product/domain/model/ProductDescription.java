package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.shared.domain.exception.AppError;
import com.albgott.catalogueservice.shared.domain.model.ValueObject;

import java.util.Objects;

public class ProductDescription extends ValueObject {
    private final String description;

    public ProductDescription() {
        description = "";
    }

    public ProductDescription(String description) {
        this.description = description == null? "" : description.trim();
    }

    @Override
    protected String value() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDescription that = (ProductDescription) o;
        return Objects.equals(description.toLowerCase(), that.description.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
