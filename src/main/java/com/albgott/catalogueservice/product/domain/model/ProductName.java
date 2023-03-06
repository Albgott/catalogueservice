package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.shared.domain.exception.AppError;
import com.albgott.catalogueservice.shared.domain.model.ValueObject;

import java.util.Objects;

public class ProductName extends ValueObject {
    private final String name;

    public ProductName() {
        name = "";
    }

    public ProductName(String name) {
        this.name = name == null? "" : name.trim().toLowerCase();
    }

    @Override
    protected void validate() {
        ifNullError(name,new AppError("productName.empty"));
        ifEmptyError(name,new AppError("productName.empty"));
    }

    @Override
    protected String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
