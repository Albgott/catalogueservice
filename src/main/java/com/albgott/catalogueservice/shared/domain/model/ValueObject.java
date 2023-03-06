package com.albgott.catalogueservice.shared.domain.model;

import com.albgott.catalogueservice.shared.domain.exception.AppError;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class ValueObject {
    private final Set<AppError> errors = new HashSet<>();

    public ValueObject() {
    }

    protected abstract String value();

    protected void error(AppError error){
        errors.add(error);
    }

    public boolean isValid(){
        validate();
        return errors.isEmpty();
    }

    public Set<AppError> errors(){
        validate();
        return errors;
    }

    protected void validate(){};

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
