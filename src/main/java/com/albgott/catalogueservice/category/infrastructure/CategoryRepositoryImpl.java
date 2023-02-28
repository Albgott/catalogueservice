package com.albgott.catalogueservice.category.infrastructure;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Optional<Category> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Category> findByBusinessId(UUID businessId) {
        return repository.findByBusinessId(businessId);
    }

    @Override
    public boolean isNameUsedOnBusiness(String name, UUID businessId) {
        return repository.existsByNameAndBusinessId(name, businessId);
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
    public List<Category> getCategoriesFromIds(List<UUID> ids){
        if(ids == null) return null;
        List<Category> categories = new ArrayList<>();
        for(UUID id: ids){
            Optional<Category> optCategory = repository.findById(id);
            if(optCategory.isEmpty()) continue;
            categories.add(optCategory.get());
        }
        return categories;
    }
}
