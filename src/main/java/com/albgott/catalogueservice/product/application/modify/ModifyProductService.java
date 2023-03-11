package com.albgott.catalogueservice.product.application.modify;

import com.albgott.catalogueservice.product.domain.model.InternalCode;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.model.ProductDescription;
import com.albgott.catalogueservice.product.domain.model.ProductName;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class ModifyProductService extends CommandUseCase<ModifyProductCommand> {
    private final ProductRepository productRepository;

    public ModifyProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    protected void doExec(ModifyProductCommand command) {
        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        modifyCode(product, command.code());
        modifyName(product, command.name());
        modifyDescription(product, command.description());

        productRepository.save(product);
    }

    private void modifyDescription(Product product, String description) {
        if (description == null) return;
        product.modifyDescription(new ProductDescription(description));
    }

    private void modifyName(Product product, String name) {
        if(name == null) return;
        ProductName productName = new ProductName(name);
        if(productRepository.isNameUsedInBusiness(productName, UUID.fromString(product.businessId()))) return;
        product.modifyName(productName);
    }

    private void modifyCode(Product product, String code) {
        if(code == null) return;
        InternalCode productCode = new InternalCode(code);
        if(productRepository.isCodeUsedInBusiness(productCode, UUID.fromString(product.businessId()))) return;
        product.modifyCode(productCode);
    }
}
