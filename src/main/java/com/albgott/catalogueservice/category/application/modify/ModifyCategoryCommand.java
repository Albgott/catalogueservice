package com.albgott.catalogueservice.category.application.modify;

import lombok.NonNull;

import java.util.UUID;

public record ModifyCategoryCommand(@NonNull UUID categoryId, String name) {
}
