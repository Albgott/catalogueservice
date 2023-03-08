package com.albgott.catalogueservice.category.domain.model;

import jakarta.persistence.*;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"business_id","name"})
})
public class Category {
    @Id
    private UUID id;
    @Column(name = "business_id",nullable = false)
    private UUID businessId;
    @Column(nullable = false)
    private String name;

    public Category() {
    }

    public Category(@NonNull UUID id,@NonNull UUID businessId,@NonNull String name) {
        this.id = id;
        this.businessId = businessId;
        setName(name);
    }

    private void setName(String name){
        this.name = name.trim().toLowerCase();
        if(StringUtils.isEmpty(this.name)) throw new RuntimeException("Category name cannot be empty");
    }

    public UUID id() {
        return id;
    }

    public UUID businessId() {
        return businessId;
    }

    public String name() {
        return name;
    }
}
