package com.albgott.catalogueservice.category.DTO;

import com.albgott.catalogueservice.category.domain.model.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {
    private String BusinessId;
    private String id;
    private String name;
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(String businessId, String id, String name, String description) {
        BusinessId = businessId;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static CategoryDTO from(Category category){
        return new CategoryDTO(
                category.businessId().toString(),
                category.id().toString(),
                category.name(),
                category.description()
        );
    }

    public static List<CategoryDTO> from(List<Category> categories){
        return categories.stream().map(CategoryDTO::from).collect(Collectors.toList());
    }

    public String businessId() {
        return BusinessId;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}
