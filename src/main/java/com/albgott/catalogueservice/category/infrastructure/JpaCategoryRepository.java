package com.albgott.catalogueservice.category.infrastructure;

import com.albgott.catalogueservice.category.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByBusinessId(UUID businessId);
    boolean existsByNameAndBusinessId(String name, UUID businessId);
    boolean existsByIdAndBusinessId(UUID id, UUID businessId);
}
