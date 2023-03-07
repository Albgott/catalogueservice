package com.albgott.catalogueservice.product.adapter.REST;

import com.albgott.catalogueservice.product.application.image.AddImageService;
import com.albgott.catalogueservice.product.application.image.AddProductImageCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class PostProductImageController {
    private final AddImageService service;

    public PostProductImageController(AddImageService service) {
        this.service = service;
    }

    @PostMapping("/products/{id}/img")
    public ResponseEntity<String> uploadImage(@PathVariable String id, @RequestParam("file") MultipartFile image){
        service.exec(new AddProductImageCommand(UUID.fromString(id),image));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
