package com.albgott.catalogueservice.product.infrastructure;

import com.albgott.catalogueservice.product.domain.model.InternalCode;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.model.ProductName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByBusinessId(UUID businessId);
    boolean existsByNameAndBusinessId(ProductName name, UUID businessId);
    boolean existsByCodeAndBusinessId(InternalCode code, UUID businessId);
    boolean existsByIdAndBusinessId(UUID id, UUID businessId);
}
