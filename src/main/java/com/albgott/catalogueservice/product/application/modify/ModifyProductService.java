package com.albgott.catalogueservice.product.application.modify;

import com.albgott.catalogueservice.product.domain.exception.ProductNotFound;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ModifyProductService implements CommandUseCase<ModifyProductCommand> {

    private final ProductRepository productRepository;
    private final EventBus eventBus;

    public ModifyProductService(ProductRepository productRepository, EventBus eventBus) {
        this.productRepository = productRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(ModifyProductCommand command) {
        Product product = getProductFromCommand(command);

        product.modifyName(command.name());
        product.modifyDescription(command.description());

        productRepository.save(product);
        eventBus.publish(product.pullDomainEvents());
    }

    private Product getProductFromCommand(ModifyProductCommand command) {
        return productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFound(command.productId().toString()));
    }
}
