package com.albgott.catalogueservice.product.domain.model;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.shared.domain.model.AggregateRoot;
import jakarta.persistence.*;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"business_id","name"})
})
public class Product extends AggregateRoot {
    private static final int MAX_IMAGES = 5;

    @Column(name = "business_id", nullable = false)
    private UUID businessId;

    @Id
    private UUID id;

    @Embedded
    @Column(name = "name", nullable = false)
    private ProductName name;
    @Embedded
    private InternalCode code;
    @Embedded
    private ProductDescription description;


    @OneToMany(fetch = FetchType.EAGER)
    private Set<File> images = new HashSet<>();

    protected Product() {
    }

    public Product(
            @NonNull UUID businessId,
            @NonNull UUID id,
            @NonNull ProductName name,
            InternalCode code,
            ProductDescription description
    ) {
        this.businessId = businessId;
        this.id = id;
        this.name = name;
        this.code = code ==null ? new InternalCode() : code;
        this.description = description ==null ? new ProductDescription() : description;
    }

    public void removeImage(File image){
        images.remove(image);
    }

    public void addImage(File image){
        if(image == null ) return;
        if(!businessId.equals(image.businessId())){return;}

        if(!canAddMoreImages()){return; }

        images.add(image);
    }

    public void modifyName(ProductName name){
        if(name == null) return;
        this.name = name;
    }

    public void modifyDescription(ProductDescription description){
        if(description == null) return;
        this.description = description;
    }

    public void modifyCode(InternalCode code){
        if(code == null) return;
        this.code = code;
    }

    public String businessId() {
        return businessId.toString();
    }

    public String id() {
        return id.toString();
    }

    public String code() {
        return code.value();
    }

    public String name() {
        return name.value();
    }

    public String description() {
        return description.value();
    }

    public Set<String> imagesIds() {
        return images.stream().map(i -> i.id().toString()).collect(Collectors.toSet());
    }

    public boolean canAddMoreImages(){
        return images.size() < MAX_IMAGES;
    }

    public void delete() {
    }
}
