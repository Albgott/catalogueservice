package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.modify.ModifyProductCommand;
import com.albgott.catalogueservice.product.application.modify.ModifyProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PutProductController {

    private final ModifyProductService service;

    public PutProductController(ModifyProductService service) {
        this.service = service;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> doPut(@PathVariable String id, @RequestBody Body body){
        service.exec(new ModifyProductCommand(UUID.fromString(id),body.name,body.description));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private record Body(String name, String description){}
}
