package com.albgott.catalogueservice.product.infrastructure;

import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository repository;

    public ProductRepositoryImpl(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findByBusinessId(UUID businessId) {
        return repository.findByBusinessId(businessId);
    }

    @Override
    public boolean isNameUsedOnBusiness(String name, UUID businessId) {
        return repository.existsByNameAndBusinessId(name, businessId);
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
