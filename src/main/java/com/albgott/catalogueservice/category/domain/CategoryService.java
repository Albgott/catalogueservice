package com.albgott.catalogueservice.category.domain;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getCategoriesFromIds(List<UUID> ids){
        if(ids == null) return new ArrayList<>();
        return ids.stream()
                .map(id -> repository.findById(id).orElse(null))
                .toList();
    }
}
