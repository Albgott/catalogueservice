package com.albgott.catalogueservice.service.domain.model;

import com.albgott.catalogueservice.category.domain.model.Category;

import java.util.Set;
import java.util.UUID;

public class Service {
    private UUID businessId;
    private UUID id;
    private String name;
    private String description;
    private Set<Category> categories;
}
