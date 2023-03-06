package com.albgott.catalogueservice.shared.domain.model;

import com.albgott.catalogueservice.shared.domain.event.DomainEvent;
import com.albgott.catalogueservice.shared.domain.exception.AppError;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private final Set<AppError> errors = new HashSet<>();

    final public List<DomainEvent> pullDomainEvents(){
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    final protected void record(DomainEvent event){
        domainEvents.add(event);
    }

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

    protected void validate(){}

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
