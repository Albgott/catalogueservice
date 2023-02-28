package com.albgott.catalogueservice.product.application.find;


import com.albgott.catalogueservice.product.DTO.ProductDTO;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SearchProductsOnBusinessService implements QueryUseCase<SearchProductsOnBusinessQuery, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductsOnBusinessService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> exec(SearchProductsOnBusinessQuery query) {
        return ProductDTO.from(
                productRepository.findByBusinessId(query.businessId())
        );
    }
}
