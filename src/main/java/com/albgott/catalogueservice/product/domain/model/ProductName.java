package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.shared.domain.model.ValueObject;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

public class ProductName extends ValueObject {
    private final String name;

    private ProductName() {
        name = "";
    }

    public ProductName(String name) {
        validate(name);
        this.name = name.trim().toLowerCase();
    }

    private void validate(String name) {
        if(name == null || StringUtils.isEmpty(name.trim())){
            throw new IllegalArgumentException();
        }
    }


    @Override
    public String value() {
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
