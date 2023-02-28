package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.DTO.ProductDTO;
import com.albgott.catalogueservice.product.application.find.FindProductByIdQuery;
import com.albgott.catalogueservice.product.application.find.FindProductByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetProductByIdController {

    private final FindProductByIdService service;

    public GetProductByIdController(FindProductByIdService service) {
        this.service = service;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> doGet(@PathVariable String id){
        ProductDTO productDTO = service.exec(new FindProductByIdQuery(UUID.fromString(id)));
        return ResponseEntity.ok(productDTO);
    }
}
