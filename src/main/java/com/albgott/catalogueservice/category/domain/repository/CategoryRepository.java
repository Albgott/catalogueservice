package com.albgott.catalogueservice.category.domain.repository;

import com.albgott.catalogueservice.category.domain.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Optional<Category> findById(UUID id);
    List<Category> findByBusinessId(UUID businessId);
    boolean isNameUsedOnBusiness(String name, UUID businessId);
    void save(Category category);
    void delete(Category category);
}
