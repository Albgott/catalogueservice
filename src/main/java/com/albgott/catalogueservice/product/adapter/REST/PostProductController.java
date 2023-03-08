package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.create.CreateProductCommand;
import com.albgott.catalogueservice.product.application.create.CreateProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PostProductController {

    private final CreateProductService service;

    public PostProductController(CreateProductService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public ResponseEntity<String> doPost(@RequestBody Body body){
        List<String> categoriesIds = body.categories_ids == null? new ArrayList<>() : body.categories_ids;
        service.exec(new CreateProductCommand(
                UUID.fromString(body.business_id),
                UUID.fromString(body.product_id),
                body.product_name,
                body.product_description,
                body.product_code,
                categoriesIds.stream().map(UUID::fromString).toList()
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private record Body(
           String business_id,
           String product_id,
           String product_name,
           String product_description,
           String product_code,
           List<String> categories_ids
    ){}

}
