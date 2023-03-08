package com.albgott.catalogueservice.product.application;

import com.albgott.catalogueservice.category.application.CategoryDTO;
import com.albgott.catalogueservice.product.domain.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class ProductResponse {
    private String business_id;
    private String id;
    private String code;
    private String name;
    private String description;
    private Set<String> images_ids;
    private Set<CategoryDTO> categories;

    public ProductResponse(Product product) {
        this.business_id = product.businessId();
        this.id = product.id();
        this.code = product.code();
        this.name = product.name();
        this.description = product.description();
        this.images_ids = new HashSet<>();
        this.categories = product.categories().stream().map(CategoryDTO::new).collect(Collectors.toSet());
    }
}
