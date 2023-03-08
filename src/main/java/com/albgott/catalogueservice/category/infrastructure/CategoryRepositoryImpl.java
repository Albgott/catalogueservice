package com.albgott.catalogueservice.category.infrastructure;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository repository;

    public CategoryRepositoryImpl(JpaCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public void delete(Category category) {
        repository.delete(category);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findAllFromBusiness(UUID businessId) {
        return repository.findByBusinessId(businessId);
    }

    @Override
    public boolean isNameUsedInBusiness(String name, UUID businessId) {
        return repository.existsByNameAndBusinessId(name, businessId);
    }

    @Override
    public boolean isIdUsedInBusiness(UUID id, UUID businessId) {
        return repository.existsByIdAndBusinessId(id,businessId);
    }
}
