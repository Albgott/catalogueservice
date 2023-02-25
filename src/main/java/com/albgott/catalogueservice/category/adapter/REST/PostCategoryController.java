package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.create.CreateCategoryCommand;
import com.albgott.catalogueservice.category.application.create.CreateCategoryService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PostCategoryController {

    private final CreateCategoryService service;

    public PostCategoryController(CreateCategoryService service) {
        this.service = service;
    }

    @PostMapping("/category")
    public ResponseEntity<String> doPost(@RequestBody Body body){
        service.exec(new CreateCategoryCommand(
                UUID.fromString(body.businessId),
                UUID.fromString(body.id),
                body.name,
                body.description
        ));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private record Body(@NonNull String businessId, @NonNull String id, @NonNull String name, String description) {
    }
}
