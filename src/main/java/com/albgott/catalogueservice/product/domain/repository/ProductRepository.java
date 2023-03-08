package com.albgott.catalogueservice.product.domain.repository;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.product.domain.model.InternalCode;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.model.ProductName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    void save(Product product);
    void delete(Product product);
    void deleteById(UUID id);
    Optional<Product> findById(UUID id);
    List<Product> findAllFromBusiness(UUID businessId);
    List<Product> findAllFromBusinessWithCategory(UUID businessId, Category category);
    boolean isNameUsedInBusiness(ProductName name, UUID businessId);
    boolean isCodeUsedInBusiness(InternalCode code, UUID businessId);
    boolean isIdUsedInBusiness(UUID id, UUID businessId);
}
