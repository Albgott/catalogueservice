package com.albgott.catalogueservice.shared.application;

public interface QueryUseCase<Q extends Query, R extends Response> {
    R exec(Q query);
}
