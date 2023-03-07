package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.ProductResponse;
import com.albgott.catalogueservice.product.application.find.FindProductByIdQuery;
import com.albgott.catalogueservice.product.application.find.FindProductByIdService;
import com.albgott.catalogueservice.product.application.find.FindProductByBusinessService;
import com.albgott.catalogueservice.product.application.find.FindProductsFromBusinessQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GetProductController {
    private final FindProductByBusinessService findProductByBusinessService;
    private final FindProductByIdService fIndProductByIdService;

    public GetProductController(
            FindProductByBusinessService findProductByBusinessService,
            FindProductByIdService findProductByIdService
    ) {
        this.findProductByBusinessService = findProductByBusinessService;
        this.fIndProductByIdService = findProductByIdService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getFromId(@PathVariable String id){
        ProductResponse response = fIndProductByIdService.exec(new FindProductByIdQuery(UUID.fromString(id)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/business/{businessId}")
    public ResponseEntity<List<ProductResponse>> getFromBusiness(@PathVariable String businessId){
        List<ProductResponse> response = findProductByBusinessService.exec(new FindProductsFromBusinessQuery(UUID.fromString(businessId)));
        return ResponseEntity.ok(response);
    }
}
