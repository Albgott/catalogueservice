package com.albgott.catalogueservice.shared.domain.exception;

public class IllegalArgumentException extends DomainException{
    public IllegalArgumentException(String code, String message) {
        super(code, message);
    }
}
