package com.albgott.catalogueservice.product.application.create;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record CreateProductCommand(
        @NonNull UUID businessId,
        @NonNull UUID productId,
        @NonNull String productName,
        String productDescription,
        String internalCode,
        List<UUID> categoriesIds
        ) {
}
