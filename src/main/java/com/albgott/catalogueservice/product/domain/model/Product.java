package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.category.domain.model.Category;
import com.albgott.catalogueservice.product.domain.event.*;
import com.albgott.catalogueservice.shared.domain.model.AggregateRoot;
import com.albgott.catalogueservice.shared.utils.StringFormatUtils;
import jakarta.persistence.*;
import lombok.NonNull;
import org.apache.commons.lang.Validate;

import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "products")
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"business_id","name"}))
public class Product extends AggregateRoot {
    @Column(name = "business_id",nullable = false)
    private UUID businessId;
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    private String description;

    @ManyToMany
    private Set<Category> categories;

    protected Product() {
    }

    public Product(@NonNull UUID businessId, @NonNull  UUID id, @NonNull  String name, String description, Set<Category> categories) {
        Validate.notEmpty(name.trim());
        this.businessId = businessId;
        this.id = id;
        this.name = StringFormatUtils.capitalize(name.trim());
        this.description = description == null? "" : description;
        this.categories = categories == null? new HashSet<>() : categories.stream().filter(c -> c.businessId().equals(businessId)).collect(Collectors.toSet());
    }

    public void modifyDescription(String description){
        if(description == null || description.trim().equals(this.description)) return;
        this.description = description.trim();
        record(new ProductDescriptionModifiedDomainEvent(this.id.toString(),this.description));
    }

    public void modifyName(String name){
        if(name == null || name.trim().equals(this.name)) return;
        this.name = StringFormatUtils.capitalize(name.trim());
        record(new ProductNameModifiedDomainEvent(this.id.toString(),this.name));
    }

    public void addCategories(List<Category> categories){
        List<Category> categoriesToAdd = new ArrayList<>();
        for(Category category: categories){
            if(!category.businessId().equals(businessId)) continue;
            if(this.categories.contains(category)) continue;
            categoriesToAdd.add(category);
        }

        if(categoriesToAdd.isEmpty()) return;

        this.categories.addAll(categoriesToAdd);
        record(
                new ProductAddedToCategoriesDomainEvent(id.toString(), getCategoriesIds(categoriesToAdd))
        );
    }

    public void removeCategories(List<Category> categories){
        List<Category> categoriesToRemove = new ArrayList<>();
        for(Category category: categories){
            if(!category.businessId().equals(businessId)) continue;
            if(!this.categories.contains(category)) continue;
            categoriesToRemove.add(category);
        }

        if(categoriesToRemove.isEmpty()) return;

        categoriesToRemove.forEach(this.categories::remove);
        record(
                new ProductRemovedFromCategoriesDomainEvent(id.toString(), getCategoriesIds(categoriesToRemove))
        );
    }

    public void delete(){
        record(new ProductDeletedDomainEvent(id.toString()));
    }

    public String[] getCategoriesIds(){
        return getCategoriesIds(this.categories.stream().toList());
    }

    private String[] getCategoriesIds(List<Category> categories){
        return categories.stream().map(c -> c.id().toString()).toArray(String[]::new);
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

    public Set<Category> categories() {
        return categories;
    }
}
