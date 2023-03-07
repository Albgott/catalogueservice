package com.albgott.catalogueservice.shared.application;

import jakarta.transaction.Transactional;

public abstract class QueryUseCase<Q, R> {
    @Transactional
    public final R exec(Q query){
        return doExec(query);
    }

    protected abstract R doExec(Q command);

}
