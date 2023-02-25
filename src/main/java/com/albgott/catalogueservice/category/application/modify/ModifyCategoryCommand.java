package com.albgott.catalogueservice.category.application.modify;

import lombok.NonNull;

import java.util.UUID;

public record ModifyCategoryCommand(@NonNull UUID id, String name, String description) {
}
