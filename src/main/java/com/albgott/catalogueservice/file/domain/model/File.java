package com.albgott.catalogueservice.file.domain.model;

import com.albgott.catalogueservice.shared.domain.exception.AppError;
import com.albgott.catalogueservice.shared.domain.model.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.util.UUID;

@Entity
public class File extends AggregateRoot {
    @Id
    private UUID id;
    @Column(name = "business_id", nullable = false)
    private UUID businessId;
    private String type;

    @Lob
    @Column(name = "data", length = 2000)
    private byte[] data;

    public File() {
    }

    public File(UUID id, UUID businessId, String type, byte[] data) {
        this.id = id;
        this.businessId = businessId;
        this.type = type;
        this.data = data;
    }

    public File(UUID id, UUID businessId, byte[] data) {
        this.id = id;
        this.businessId = businessId;
        this.data = data;
    }

    public UUID id() {
        return id;
    }

    public UUID businessId() {
        return businessId;
    }

    public String type() {
        return type;
    }

    public byte[] data() {
        return data;
    }

    @Override
    protected void validate() {
        ifNullError(id, new AppError("file.id.empty"));
        ifNullError(businessId, new AppError("file.businessId.empty"));
        ifEmptyError(type, new AppError("file.type.empty"));
        if(data.length == 0) error(new AppError("file.data.empty"));
    }
}
