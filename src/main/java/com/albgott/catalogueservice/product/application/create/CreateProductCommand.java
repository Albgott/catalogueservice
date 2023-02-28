package com.albgott.catalogueservice.product.application.create;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record CreateProductCommand(@NonNull UUID businessId, @NonNull UUID id, @NonNull String name,
                                   String description, List<UUID> categoryIds) {
}
