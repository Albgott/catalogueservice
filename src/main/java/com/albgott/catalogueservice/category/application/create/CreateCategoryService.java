package com.albgott.catalogueservice.category.application.create;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CreateCategoryService extends CommandUseCase<CreateCategoryCommand> {

    private final CategoryRepository categoryRepository;

    public CreateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doExec(CreateCategoryCommand command) {
        Category category = new Category(
          command.categoryId(),
          command.businessId(),
          command.name()
        );
        if(categoryRepository.isNameUsedInBusiness(category.name(), category.businessId())){
            throw new RuntimeException("Category name on use");
        }
        if(categoryRepository.isIdUsedInBusiness(category.id(), category.businessId())){
            throw new RuntimeException("Category id on use");
        }
        categoryRepository.save(category);
    }
}
