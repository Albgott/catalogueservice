package com.albgott.catalogueservice.shared.infrastructure.exception;

import com.albgott.catalogueservice.shared.domain.exception.DomainException;

public class ErrorMessage {

    private final String exception;
    private final String code;
    private final String message;
    private final String path;

    public ErrorMessage(DomainException exception, String path) {
        this.exception = exception.getClass().getSimpleName();
        this.code = exception.code();
        this.message = exception.message();
        this.path = path;
    }

    @Override
    public String toString() {
        return "{" +
                "exception='" + exception + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
