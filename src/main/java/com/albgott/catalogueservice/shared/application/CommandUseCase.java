package com.albgott.catalogueservice.shared.application;

import com.albgott.catalogueservice.shared.domain.exception.AppError;
import com.albgott.catalogueservice.shared.domain.exception.PackageException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class CommandUseCase<C> {
    private final Set<AppError> errors = new HashSet<>();

    @Transactional
    public final void exec(C command){
        validateData(command);
        doExec(command);
    }

    protected abstract void doExec(C command);

    private void validateData(C command) {
    }

    protected void throwIfErrors(){
        if(!errors.isEmpty())
            throw new PackageException(errors);
    }

    protected void throwIfErrors(Set<AppError> moreErrors){
        errors.addAll(moreErrors);
        if(!errors.isEmpty())
            throw new PackageException(errors);
    }

    protected void error(AppError error){
        errors.add(error);
    }

    protected void ifNullError(Object object, AppError error){
        if(object == null) errors.add(error);
    }

    protected void ifEmptyError(String text, AppError error){
        if(StringUtils.isEmpty(text)) errors.add(error);
    }

    protected void ifEmptyError(Collection<?> collection, AppError error){
        if(collection.isEmpty()) errors.add(error);
    }
}
