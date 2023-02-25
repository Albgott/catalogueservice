package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.delete.DeleteCategoryCommand;
import com.albgott.catalogueservice.category.application.delete.DeleteCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteCategoryByIdController {

    private final DeleteCategoryService service;

    public DeleteCategoryByIdController(DeleteCategoryService service) {
        this.service = service;
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> doDelete(@PathVariable String id){
        service.exec(new DeleteCategoryCommand(UUID.fromString(id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
