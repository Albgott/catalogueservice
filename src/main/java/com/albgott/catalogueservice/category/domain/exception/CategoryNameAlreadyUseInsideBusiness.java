package com.albgott.catalogueservice.category.domain.exception;

import com.albgott.catalogueservice.shared.domain.exception.IllegalArgumentException;

public class CategoryNameAlreadyUseInsideBusiness extends IllegalArgumentException {
    public CategoryNameAlreadyUseInsideBusiness(String businessId, String name) {
        super("CATEGORY_NAME_ALREADY_ON_USE", String.format("There is already a category with name <%s> inside " +
                "business with id <%s>.",name,businessId));
    }
}
