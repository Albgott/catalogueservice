package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.DTO.ProductDTO;
import com.albgott.catalogueservice.product.application.find.SearchProductsOnBusinessQuery;
import com.albgott.catalogueservice.product.application.find.SearchProductsOnBusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GetBusinessProductsController {
    private final SearchProductsOnBusinessService service;

    public GetBusinessProductsController(SearchProductsOnBusinessService service) {
        this.service = service;
    }

    @GetMapping("/products/business/{id}")
    public ResponseEntity<List<ProductDTO>> doGet(@PathVariable(name = "id") String businessId){
        List<ProductDTO> productDTOS = service.exec(new SearchProductsOnBusinessQuery(UUID.fromString(businessId)));
        return ResponseEntity.ok(productDTOS);
    }
}
