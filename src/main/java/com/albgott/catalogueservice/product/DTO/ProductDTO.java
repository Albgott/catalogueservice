package com.albgott.catalogueservice.product.DTO;

import com.albgott.catalogueservice.product.domain.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDTO {

    private String businessId;
    private String id;
    private String name;
    private String description;
    private List<CategoryDTO> categories;

    public ProductDTO(String businessId, String id, String name, String description, List<CategoryDTO> categories) {
        this.businessId = businessId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public static ProductDTO from(Product product){
        List<CategoryDTO> categoryDTOS = product.categories().stream().map(
                c -> new CategoryDTO(c.id().toString(),c.name())
        ).toList();

        return new ProductDTO(
                product.businessId().toString(),
                product.id().toString(),
                product.name(),
                product.description(),
                categoryDTOS
        );
    }

    public static List<ProductDTO> from(List<Product> products){
        return products.stream().map(ProductDTO::from).toList();
    }

    private record CategoryDTO(String id, String name){}

    public String businessId() {
        return businessId;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public List<CategoryDTO> categories() {
        return categories;
    }
}
