package com.albgott.catalogueservice.category.application.create;

import lombok.NonNull;

import java.util.UUID;

public record CreateCategoryCommand(@NonNull UUID businessId, @NonNull UUID id, @NonNull String name, String description) {
}
