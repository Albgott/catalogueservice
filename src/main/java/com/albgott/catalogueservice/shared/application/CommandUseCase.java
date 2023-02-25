package com.albgott.catalogueservice.shared.application;

public interface CommandUseCase<C> {
    void exec(C command);
}
