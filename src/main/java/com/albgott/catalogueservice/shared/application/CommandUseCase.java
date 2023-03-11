package com.albgott.catalogueservice.shared.application;

import jakarta.transaction.Transactional;
import java.io.IOException;

public abstract class CommandUseCase<C> {


    @Transactional
    public final void exec(C command){
        doExec(command);
    }

    protected abstract void doExec(C command);

}
