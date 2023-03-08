package com.albgott.catalogueservice.category.adapter.REST;

import com.albgott.catalogueservice.category.application.CategoryDTO;
import com.albgott.catalogueservice.category.application.find.FindCategoriesFromBusinessQuery;
import com.albgott.catalogueservice.category.application.find.FindCategoriesFromBusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class GetCategoryController {
    private final FindCategoriesFromBusinessService service;

    public GetCategoryController(FindCategoriesFromBusinessService service) {
        this.service = service;
    }

    @GetMapping("/categories/business/{id}")
    public ResponseEntity<List<CategoryDTO>> doGet(@PathVariable String id){
        List<CategoryDTO> categoryDTOS = service.exec(new FindCategoriesFromBusinessQuery(UUID.fromString(id)));
        return ResponseEntity.ok(categoryDTOS);
    }
}
