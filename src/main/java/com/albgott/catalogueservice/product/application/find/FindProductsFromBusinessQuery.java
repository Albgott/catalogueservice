package com.albgott.catalogueservice.product.application.find;

import lombok.NonNull;

import java.util.UUID;

public record FindProductsFromBusinessQuery(@NonNull UUID businessId) {
}
