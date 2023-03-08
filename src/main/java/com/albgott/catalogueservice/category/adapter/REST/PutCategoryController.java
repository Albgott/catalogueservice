package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.modify.ModifyCategoryCommand;
import com.albgott.catalogueservice.category.application.modify.ModifyCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PutCategoryController {
    private final ModifyCategoryService service;

    public PutCategoryController(ModifyCategoryService service) {
        this.service = service;
    }

    @PutMapping("/categories/{id}")
    private ResponseEntity<String> doPut(@PathVariable String id, @RequestBody Body body){
        service.exec(new ModifyCategoryCommand(
                UUID.fromString(id),
                body.category_name
        ));
        return ResponseEntity.ok().build();
    }

    private record Body(String category_name){}
}
