package com.albgott.catalogueservice.category.application.find;

import lombok.NonNull;

import java.util.UUID;

public record FindCategoryByIdQuery(@NonNull UUID id) {
}
