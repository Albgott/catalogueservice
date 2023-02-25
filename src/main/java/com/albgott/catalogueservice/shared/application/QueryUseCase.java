package com.albgott.catalogueservice.shared.application;

public interface QueryUseCase<Q, R> {
    R exec(Q query);
}
