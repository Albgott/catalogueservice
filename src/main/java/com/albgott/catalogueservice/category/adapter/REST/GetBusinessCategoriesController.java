package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.DTO.CategoryDTO;
import com.albgott.catalogueservice.category.application.find.FindAllBusinessCategoriesQuery;
import com.albgott.catalogueservice.category.application.find.FindAllBusinessCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GetBusinessCategoriesController {

    private final FindAllBusinessCategoriesService service;

    public GetBusinessCategoriesController(FindAllBusinessCategoriesService service) {
        this.service = service;
    }

    @GetMapping("/categories/business/{id}")
    public ResponseEntity<List<CategoryDTO>> doGet(@PathVariable String id){
        List<CategoryDTO> categoryDTOS = service.exec(new FindAllBusinessCategoriesQuery(UUID.fromString(id)));

        return ResponseEntity.ok(categoryDTOS);
    }
}
