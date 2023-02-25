package com.albgott.catalogueservice.category.application.find;

import com.albgott.catalogueservice.category.DTO.CategoryDTO;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllBusinessCategoriesService implements QueryUseCase<FindAllBusinessCategoriesQuery,
        List<CategoryDTO>> {

    private final CategoryRepository categoryRepository;

    public FindAllBusinessCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> exec(FindAllBusinessCategoriesQuery query) {
        return CategoryDTO.from(
                categoryRepository.findByBusinessId(query.businessId())
        );
    }
}
