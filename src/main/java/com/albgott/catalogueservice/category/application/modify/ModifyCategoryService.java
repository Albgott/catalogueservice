package com.albgott.catalogueservice.category.application.modify;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ModifyCategoryService extends CommandUseCase<ModifyCategoryCommand> {
    private final CategoryRepository categoryRepository;

    public ModifyCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doExec(ModifyCategoryCommand command) throws IOException {
        Category category = categoryRepository.findById(command.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.modifyName(command.name());

        categoryRepository.save(category);
    }
}
