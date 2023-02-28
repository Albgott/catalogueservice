package com.albgott.catalogueservice.product.application.delete;

import com.albgott.catalogueservice.product.domain.exception.ProductNotFound;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import com.albgott.catalogueservice.shared.domain.event.EventBus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteProductService implements CommandUseCase<DeleteProductCommand> {

    private final ProductRepository productRepository;
    private final EventBus eventBus;

    public DeleteProductService(ProductRepository productRepository, EventBus eventBus) {
        this.productRepository = productRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void exec(DeleteProductCommand command) {
        Product product = getProductFromCommand(command);

        product.delete();
        productRepository.delete(product);
        eventBus.publish(product.pullDomainEvents());
    }

    private Product getProductFromCommand(DeleteProductCommand command) {
        return productRepository.findById(command.productId())
                .orElseThrow(() -> new ProductNotFound(command.productId().toString()));
    }
}
