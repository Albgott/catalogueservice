package com.albgott.catalogueservice.product.application.find;

import lombok.NonNull;

import java.util.UUID;

public record FindProductByIdQuery(@NonNull UUID productId) {
}
