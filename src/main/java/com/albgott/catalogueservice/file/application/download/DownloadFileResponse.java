package com.albgott.catalogueservice.file.application.download;

import com.albgott.catalogueservice.file.domain.model.File;

public class DownloadFileResponse {
    private String id;
    private String type;
    private byte[] data;

    public DownloadFileResponse(File file){
        this.id = file.id().toString();
        this.type = file.type();
        this.data = file.data();
    }

    public DownloadFileResponse() {
    }

    public String id() {
        return id;
    }

    public String type() {
        return type;
    }

    public byte[] data() {
        return data;
    }
}
