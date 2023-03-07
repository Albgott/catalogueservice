package com.albgott.catalogueservice.file.domain.model;

import com.albgott.catalogueservice.file.domain.service.ImageUtils;
import com.albgott.catalogueservice.shared.domain.model.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.NonNull;

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

    public File(@NonNull UUID id,@NonNull UUID businessId,@NonNull String type , byte[] data) {
        this.id = id;
        this.businessId = businessId;
        this.data = ImageUtils.compressImage(data);
        this.type = type;
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
        return ImageUtils.decompressImage(data);
    }

}
