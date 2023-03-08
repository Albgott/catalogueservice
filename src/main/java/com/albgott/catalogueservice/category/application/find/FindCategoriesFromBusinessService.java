package com.albgott.catalogueservice.category.application.find;

import com.albgott.catalogueservice.category.application.CategoryDTO;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCategoriesFromBusinessService extends QueryUseCase<FindCategoriesFromBusinessQuery, List<CategoryDTO>> {

    private final CategoryRepository repository;

    public FindCategoriesFromBusinessService(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    protected List<CategoryDTO> doExec(FindCategoriesFromBusinessQuery command) {
        List<Category> categories = repository.findAllFromBusiness(command.businessId());
        return categories.stream().map(CategoryDTO::new).toList();
    }
}
