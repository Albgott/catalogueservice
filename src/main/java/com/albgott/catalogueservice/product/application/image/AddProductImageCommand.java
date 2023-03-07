package com.albgott.catalogueservice.product.application.image;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record AddProductImageCommand(@NonNull UUID productId, @NonNull MultipartFile imageFile) {
}
