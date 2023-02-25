package com.albgott.catalogueservice.category.domain.model;

import com.albgott.catalogueservice.category.domain.event.CategoryDeletedDomainEvent;
import com.albgott.catalogueservice.category.domain.event.CategoryDescriptionModifiedDomainEvent;
import com.albgott.catalogueservice.category.domain.event.CategoryNameModifiedDomainEvent;
import com.albgott.catalogueservice.shared.domain.model.AggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NonNull;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang.WordUtils;

import java.util.UUID;

@Entity(name = "categories")
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = {"business_id","name"}))
public class Category extends AggregateRoot {
    private UUID businessId;
    @Id
    private UUID id;
    private String name;
    private String description;

    protected Category() {
    }

    public Category(@NonNull UUID businessId,@NonNull UUID id,@NonNull String name, String description) {
        Validate.notEmpty(name.trim());
        this.businessId = businessId;
        this.id = id;
        this.name = WordUtils.capitalize(name.trim());
        this.description = description == null? "" : description.trim();
    }

    public Category(UUID businessId, UUID id, String name) {
        this(businessId,id,name,null);
    }

    public void modifyName(String value){
        if(value == null || value.trim().equals(name)) return;
        this.name = WordUtils.capitalize(name.trim());
        record(new CategoryNameModifiedDomainEvent(id.toString(),name));
    }

    public void modifyDescription(String value){
        if(value == null || value.trim().equals(description)) return;
        this.description = description.trim();
        record(new CategoryDescriptionModifiedDomainEvent(id.toString(),description));
    }

    public void delete(){
        record(new CategoryDeletedDomainEvent(this.id.toString()));
    }

    public UUID businessId() {
        return businessId;
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}
