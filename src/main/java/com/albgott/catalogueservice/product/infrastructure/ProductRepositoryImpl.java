package com.albgott.catalogueservice.product.infrastructure;

import com.albgott.catalogueservice.product.domain.model.InternalCode;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.model.ProductName;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import org.apache.commons.lang.StringUtils;
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
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> findAllFromBusiness(UUID businessId) {
        return repository.findByBusinessId(businessId);
    }

    @Override
    public boolean isNameUsedInBusiness(ProductName name, UUID businessId) {
        return repository.existsByNameAndBusinessId(name, businessId);
    }

    @Override
    public boolean isCodeUsedInBusiness(InternalCode code, UUID businessId) {
        return !StringUtils.isEmpty(code.value()) && repository.existsByCodeAndBusinessId(code, businessId);
    }

    @Override
    public boolean isIdUsedInBusiness(UUID id, UUID businessId) {
        return repository.existsByIdAndBusinessId(id, businessId);
    }
}
