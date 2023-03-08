package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.manage_categories.ManageCategoriesCommand;
import com.albgott.catalogueservice.product.application.manage_categories.ManageProductCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class DeleteProductCategoriesController {
    private final ManageProductCategoriesService service;

    public DeleteProductCategoriesController(ManageProductCategoriesService service) {
        this.service = service;
    }

    @DeleteMapping("/products/{id}/categories")
    public ResponseEntity<String> doPut(@PathVariable String id, @RequestBody Body body){
        List<String> categoriesIds = body.categories_ids == null? new ArrayList<>() : body.categories_ids;
        service.exec(new ManageCategoriesCommand(
                UUID.fromString(id),
                null,
                categoriesIds.stream().map(UUID::fromString).toList()
        ));
        return ResponseEntity.ok().build();
    }

    private record Body(List<String> categories_ids){}
}
