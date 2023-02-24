package com.albgott.catalogueservice.shared.application;

public interface CommandUseCase<C extends Command> {
    void exec(C command);
}
