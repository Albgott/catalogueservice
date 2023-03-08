package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.manage_categories.ManageCategoriesCommand;
import com.albgott.catalogueservice.product.application.manage_categories.ManageProductCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class PutProductCategoriesController {
    private final ManageProductCategoriesService service;

    public PutProductCategoriesController(ManageProductCategoriesService service) {
        this.service = service;
    }

    @PutMapping("/products/{id}/categories")
    public ResponseEntity<String> doPut(@PathVariable String id, @RequestBody Body body){
        List<String> categoriesIds = body.categories_ids == null? new ArrayList<>() : body.categories_ids;
        service.exec(new ManageCategoriesCommand(
                UUID.fromString(id),
                categoriesIds.stream().map(UUID::fromString).toList(),
                null
        ));
        return ResponseEntity.ok().build();
    }

    private record Body(List<String> categories_ids){}
}
