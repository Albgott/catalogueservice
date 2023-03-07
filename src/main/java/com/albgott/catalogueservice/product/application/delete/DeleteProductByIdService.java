package com.albgott.catalogueservice.product.application.delete;

import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductByIdService extends CommandUseCase<DeleteProductByIdCommand> {

    private final ProductRepository productRepository;

    public DeleteProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    protected void doExec(DeleteProductByIdCommand command) {
        Product product = productRepository.findById(command.productId())
                .orElseThrow( () -> new RuntimeException("Product not found"));

        product.delete();
        productRepository.delete(product);
    }
}
