package com.albgott.catalogueservice.shared.domain.exception;

public class NotFoundExeption extends DomainException{
    public NotFoundExeption(String code, String message) {
        super(code, message);
    }
}
