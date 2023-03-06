package com.albgott.catalogueservice.shared.domain.exception;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PackageException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    public PackageException(Set<AppError> errors) {
        this.errors = errors.stream().map(AppError::errorCode).toList();
    }

    public List<String> errors() {
        return errors;
    }
}
