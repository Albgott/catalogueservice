package com.albgott.catalogueservice.product.application.image;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.file.domain.repository.FileRepository;
import com.albgott.catalogueservice.product.domain.model.Product;
import com.albgott.catalogueservice.product.domain.repository.ProductRepository;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class AddImageService extends CommandUseCase<AddProductImageCommand> {
    private final ProductRepository productRepository;
    private final FileRepository fileRepository;

    public AddImageService(ProductRepository productRepository, FileRepository fileRepository) {
        this.productRepository = productRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    protected void doExec(AddProductImageCommand command) {
        Product product = productRepository.findById(command.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(!product.canAddMoreImages()) throw new RuntimeException("Maximum images reached");

        File image = null;
        try {
            image = new File(
               UUID.randomUUID(),
               UUID.fromString(product.businessId()),
               Objects.requireNonNull(command.imageFile().getContentType()),
               command.imageFile().getBytes()
            );
        } catch (IOException ignore) {}
        fileRepository.save(image);
        product.addImage(image);
        productRepository.save(product);
    }
}
