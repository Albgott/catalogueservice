package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.shared.domain.model.ValueObject;

import java.util.Objects;

public class InternalCode extends ValueObject {
    private final String code;

    public InternalCode() {
        code = "";
    }

    public InternalCode(String code) {
        this.code = code == null?  "" :  code.trim().toUpperCase();
    }

    @Override
    protected String value() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalCode that = (InternalCode) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
