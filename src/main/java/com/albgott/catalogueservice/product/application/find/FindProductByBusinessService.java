package com.albgott.catalogueservice.product.application.find;

import com.albgott.catalogueservice.product.application.ProductResponse;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductByBusinessService extends QueryUseCase<FindProductsFromBusinessQuery, List<ProductResponse>> {

    private final ProductRepository repository;

    public FindProductByBusinessService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    protected List<ProductResponse> doExec(FindProductsFromBusinessQuery command) {
        List<Product> products = repository.findAllFromBusiness(command.businessId());
        return products.stream().map(ProductResponse::new).toList();
    }
}
