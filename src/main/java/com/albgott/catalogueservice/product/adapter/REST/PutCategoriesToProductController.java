package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.manageCategories.ManageCategoriesCommand;
import com.albgott.catalogueservice.product.application.manageCategories.ManageCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class PutCategoriesToProductController {

    private final ManageCategoriesService service;

    public PutCategoriesToProductController(ManageCategoriesService service) {
        this.service = service;
    }

    @PutMapping("/products/{id}/categories")
    public ResponseEntity<String> doPut(@PathVariable String id, @RequestBody Body body){
        service.exec(new ManageCategoriesCommand(
                UUID.fromString(id),
                Arrays.stream(body.categories).map(UUID::fromString).toList(),
                null
        ));
        return ResponseEntity.ok().build();
    }

    private record Body(String[] categories){}
}
