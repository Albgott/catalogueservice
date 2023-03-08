package com.albgott.catalogueservice.category.application.delete;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeleteCategoryByIdService extends CommandUseCase<DeleteCategoryCommand> {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryByIdService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doExec(DeleteCategoryCommand command) throws IOException {
        Category category = categoryRepository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.delete();

        categoryRepository.delete(category);
    }
}
