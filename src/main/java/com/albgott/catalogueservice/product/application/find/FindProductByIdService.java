package com.albgott.catalogueservice.product.application.find;

import com.albgott.catalogueservice.product.application.ProductResponse;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdService extends QueryUseCase<FindProductByIdQuery, ProductResponse> {

    private final ProductRepository repository;

    public FindProductByIdService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ProductResponse doExec(FindProductByIdQuery command) {
        Product product = repository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new ProductResponse(product);
    }
}
