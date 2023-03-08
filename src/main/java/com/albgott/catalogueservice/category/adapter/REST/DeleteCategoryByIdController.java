package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.delete.DeleteCategoryByIdService;
import com.albgott.catalogueservice.category.application.delete.DeleteCategoryCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteCategoryByIdController {
    private final DeleteCategoryByIdService service;

    public DeleteCategoryByIdController(DeleteCategoryByIdService service) {
        this.service = service;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> doDelete(@PathVariable String id){
        service.exec(new DeleteCategoryCommand(UUID.fromString(id)));
        return ResponseEntity.ok().build();
    }
}
