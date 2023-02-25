package com.albgott.catalogueservice.category.domain.exception;

import com.albgott.catalogueservice.shared.domain.exception.NotFoundExeption;

public class CategoryNotFound extends NotFoundExeption {
    public CategoryNotFound(String id) {
        super("CATEGORY_NOT_FOUND", String.format("Category with id <%s> not found.",id));
    }
}
