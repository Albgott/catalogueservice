package com.albgott.catalogueservice.category.application.find;

import com.albgott.catalogueservice.category.DTO.CategoryDTO;
import com.albgott.catalogueservice.category.domain.exception.CategoryNotFound;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

@Service
public class FindCategoryByIdService implements QueryUseCase<FindCategoryByIdQuery, CategoryDTO> {

    private final CategoryRepository categoryRepository;

    public FindCategoryByIdService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO exec(FindCategoryByIdQuery query) {
        Category category = categoryRepository.findById(query.id())
                .orElseThrow(() -> new CategoryNotFound(query.id().toString()));

        return CategoryDTO.from(category);
    }
}
