package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.delete.DeleteProductCommand;
import com.albgott.catalogueservice.product.application.delete.DeleteProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteProductController {

    private final DeleteProductService service;

    public DeleteProductController(DeleteProductService service) {
        this.service = service;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> doDelete(@PathVariable String id){
        service.exec(new DeleteProductCommand(UUID.fromString(id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
