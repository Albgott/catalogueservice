package com.albgott.catalogueservice.product.domain.repository;

import com.albgott.catalogueservice.product.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Optional<Product> findById(UUID id);
    List<Product> findByBusinessId(UUID businessId);
    boolean isNameUsedOnBusiness(String name, UUID businessId);
    void save(Product product);
    void delete(Product product);
}
