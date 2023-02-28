package com.albgott.catalogueservice.product.application.create;

import com.albgott.catalogueservice.category.domain.exception.CategoryNameAlreadyUseInsideBusiness;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.product.domain.event.ProductCreatedDomainEvent;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CreateProductService implements CommandUseCase<CreateProductCommand> {

    private final EventBus eventBus;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CreateProductService(EventBus eventBus, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.eventBus = eventBus;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void exec(CreateProductCommand command) {
        Product product = createProductFromCommand(command);

        productRepository.save(product);
        eventBus.publish(List.of(
                new ProductCreatedDomainEvent(product)
        ));
    }

    private Product createProductFromCommand(CreateProductCommand command) {
        Set<Category> categories = new HashSet<>(categoryRepository.getCategoriesFromIds(command.categoryIds()));
        Product product = new Product(
                command.businessId(),
                command.id(),
                command.name(),
                command.description(),
                categories
        );

        if(productRepository.isNameUsedOnBusiness(product.name(), product.businessId()))
            throw new CategoryNameAlreadyUseInsideBusiness(product.businessId().toString(),product.name());

        return product;
    }
}
