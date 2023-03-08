package com.albgott.catalogueservice.product.application.create;

import com.albgott.catalogueservice.category.domain.CategoryService;
import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.category.domain.repository.CategoryRepository;
import com.albgott.catalogueservice.product.domain.model.InternalCode;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.model.ProductDescription;
import com.albgott.catalogueservice.product.domain.model.ProductName;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CreateProductService extends CommandUseCase<CreateProductCommand> {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CreateProductService(ProductRepository productRepository,
                                CategoryRepository categoryRepository,
                                CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }


    @Override
    protected void doExec(CreateProductCommand command) {
        Product product = getFromCommand(command);

        productRepository.save(product);
    }

    private Product getFromCommand(CreateProductCommand command) {
        Set<Category> categories = new HashSet<>(categoryService.getCategoriesFromIds(command.categoriesIds()));
        UUID businessId = command.businessId();
        UUID id = command.productId();
        ProductName name = new ProductName(command.productName());
        ProductDescription description = new ProductDescription(command.productDescription());
        InternalCode code = new InternalCode(command.internalCode());

        if(
                productRepository.isCodeUsedInBusiness(code,businessId)
                || productRepository.isNameUsedInBusiness(name,businessId)
                || productRepository.isIdUsedInBusiness(id, businessId)
        ) throw new RuntimeException("Cant create product");

        return new Product(businessId,id,name,code,description, categories);
    }

}
