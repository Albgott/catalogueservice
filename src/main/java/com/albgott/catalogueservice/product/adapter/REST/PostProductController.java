package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.create.CreateProductCommand;
import com.albgott.catalogueservice.product.create.CreateProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class PostProductController {
    private final CreateProductService service;

    public PostProductController(CreateProductService service) {
        this.service = service;
    }

    @PostMapping("/products")
    private ResponseEntity<String> doPost(@RequestBody Body body){
        service.exec(
                new CreateProductCommand(
                        UUID.fromString(body.businessId),
                        UUID.fromString(body.productId),
                        body.name,
                        body.description,
                        Arrays.stream(body.categories).map(UUID::fromString).toList()
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private record Body(String businessId, String productId, String name, String description, String[] categories){}
}
