package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.category.domain.model.Category;

import java.util.Set;
import java.util.UUID;

public class Product {
    private UUID businessId;
    private UUID id;
    private String name;
    private String description;
    private Set<Category> categories;
}
