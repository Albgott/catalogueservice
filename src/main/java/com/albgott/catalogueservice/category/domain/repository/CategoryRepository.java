package com.albgott.catalogueservice.category.domain.repository;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.product.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    void save(Category category);
    void delete(Category category);
    void deleteById(UUID id);
    Optional<Category> findById(UUID id);
    List<Category> findAllFromBusiness(UUID businessId);
    boolean isNameUsedInBusiness(String name, UUID businessId);
    boolean isIdUsedInBusiness(UUID id, UUID businessId);
}
