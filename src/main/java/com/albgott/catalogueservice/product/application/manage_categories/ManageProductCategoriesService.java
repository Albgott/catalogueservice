package com.albgott.catalogueservice.product.application.manage_categories;

import com.albgott.catalogueservice.category.domain.CategoryService;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ManageProductCategoriesService extends CommandUseCase<ManageCategoriesCommand> {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ManageProductCategoriesService(CategoryService categoryService,
                                          ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doExec(ManageCategoriesCommand command) throws IOException {
        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Category> categoriesToAdd = categoryService.getCategoriesFromIds(command.categoriesToAdd());
        categoriesToAdd.forEach(product::addToCategory);

        List<Category> categoriesToRemove = categoryService.getCategoriesFromIds(command.categoriesToRemove());
        categoriesToRemove.forEach(product::removeFromCategory);

        productRepository.save(product);
    }

}
