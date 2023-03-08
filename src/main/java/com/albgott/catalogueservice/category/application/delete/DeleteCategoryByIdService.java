package com.albgott.catalogueservice.category.application.delete;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DeleteCategoryByIdService extends CommandUseCase<DeleteCategoryCommand> {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DeleteCategoryByIdService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    protected void doExec(DeleteCategoryCommand command) throws IOException {
        Category category = categoryRepository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Product> productsOfCategory = productRepository.findAllFromBusinessWithCategory(category.businessId(),
                category);

        productsOfCategory.forEach(p -> {
            p.removeFromCategory(category);
            productRepository.save(p);
        });

        category.delete();
        categoryRepository.delete(category);
    }
}
