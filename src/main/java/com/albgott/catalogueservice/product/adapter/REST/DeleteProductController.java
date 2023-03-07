package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.delete.DeleteProductByIdCommand;
import com.albgott.catalogueservice.product.application.delete.DeleteProductByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteProductController {

    private final DeleteProductByIdService service;

    public DeleteProductController(DeleteProductByIdService service) {
        this.service = service;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> doDelete(@PathVariable String id){
        service.exec(new DeleteProductByIdCommand(UUID.fromString(id)));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
