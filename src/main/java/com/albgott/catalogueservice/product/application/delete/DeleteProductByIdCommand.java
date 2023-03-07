package com.albgott.catalogueservice.product.application.delete;

import lombok.NonNull;

import java.util.UUID;

public record DeleteProductByIdCommand(@NonNull UUID productId) {
}
