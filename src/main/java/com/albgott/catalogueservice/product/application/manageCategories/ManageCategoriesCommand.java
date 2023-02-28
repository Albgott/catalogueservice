package com.albgott.catalogueservice.product.application.manageCategories;

import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public record ManageCategoriesCommand(@NonNull UUID productId, List<UUID> categoriesToAdd,
                                      List<UUID> categoriesToRemove) {
}
