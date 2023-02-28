package com.albgott.catalogueservice.product.application.manageCategories;

import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.product.domain.exception.ProductNotFound;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ManageCategoriesService implements CommandUseCase<ManageCategoriesCommand> {

    private final ProductRepository productRepository;
    private final EventBus eventBus;
    private final CategoryRepository categoryRepository;

    public ManageCategoriesService(
                                   ProductRepository productRepository,
                                   EventBus eventBus, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(ManageCategoriesCommand command) {
        Product product = getProductFromCommand(command);

        product.addCategories(categoryRepository.getCategoriesFromIds(command.categoriesToAdd()));
        product.removeCategories(categoryRepository.getCategoriesFromIds(command.categoriesToRemove()));

        productRepository.save(product);
        eventBus.publish(product.pullDomainEvents());
    }

    private Product getProductFromCommand(ManageCategoriesCommand command) {
        return productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFound(command.productId().toString()));
    }


}
