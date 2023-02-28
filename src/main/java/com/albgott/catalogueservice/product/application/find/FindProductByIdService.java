package com.albgott.catalogueservice.product.application.find;

import com.albgott.catalogueservice.product.DTO.ProductDTO;
import com.albgott.catalogueservice.product.domain.exception.ProductNotFound;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FindProductByIdService implements QueryUseCase<FindProductByIdQuery, ProductDTO> {

    private final ProductRepository productRepository;

    public FindProductByIdService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO exec(FindProductByIdQuery query) {
        Product product = productRepository.findById(query.productId())
                .orElseThrow(() -> new ProductNotFound(query.productId().toString()));

        return ProductDTO.from(product);
    }
}
