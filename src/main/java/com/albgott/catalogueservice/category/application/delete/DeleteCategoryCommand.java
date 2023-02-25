package com.albgott.catalogueservice.category.application.delete;

import lombok.NonNull;

import java.util.UUID;

public record DeleteCategoryCommand(@NonNull UUID id) {
}
