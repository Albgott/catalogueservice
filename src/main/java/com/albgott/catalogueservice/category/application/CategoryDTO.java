package com.albgott.catalogueservice.category.application;

import com.albgott.catalogueservice.category.domain.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.id().toString();
        this.name = category.name();
    }
}
