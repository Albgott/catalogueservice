package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.manageCategories.ManageCategoriesCommand;
import com.albgott.catalogueservice.product.application.manageCategories.ManageCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class DeleteCategoriesFromProductController {

    private final ManageCategoriesService service;

    public DeleteCategoriesFromProductController(ManageCategoriesService service) {
        this.service = service;
    }

    @DeleteMapping("/products/{id}/categories")
    public ResponseEntity<String> doDelete(@PathVariable String id, @RequestBody Body body){
        service.exec(new ManageCategoriesCommand(
                UUID.fromString(id),
                null,
                Arrays.stream(body.categories).map(UUID::fromString).toList()
        ));
        return ResponseEntity.ok().build();
    }

    private record Body(String[] categories){}
}
