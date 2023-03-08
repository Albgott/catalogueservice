package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.create.CreateCategoryCommand;
import com.albgott.catalogueservice.category.application.create.CreateCategoryService;
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

    @PostMapping("/categories")
    public ResponseEntity<String> doPost(@RequestBody Body body){
        service.exec(new CreateCategoryCommand(
                UUID.fromString(body.business_id()),
                UUID.fromString(body.category_id),
                body.category_name
        ));

        return ResponseEntity.ok().build();
    }

    private record Body(String business_id, String category_id, String category_name){}
}
