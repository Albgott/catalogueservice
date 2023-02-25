package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.DTO.CategoryDTO;
import com.albgott.catalogueservice.category.application.find.FindCategoryByIdQuery;
import com.albgott.catalogueservice.category.application.find.FindCategoryByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetCategoryByIdController {

    private final FindCategoryByIdService service;

    public GetCategoryByIdController(FindCategoryByIdService service) {
        this.service = service;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> doGet(@PathVariable String id){
        CategoryDTO category = service.exec(new FindCategoryByIdQuery(UUID.fromString(id)));

        return ResponseEntity.ok(category);
    }

}
