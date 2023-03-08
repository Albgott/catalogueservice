package com.albgott.catalogueservice.product.application.modify;

import lombok.NonNull;

import java.util.UUID;

public record ModifyProductCommand(@NonNull UUID productId, String name, String description, String code) {
}
