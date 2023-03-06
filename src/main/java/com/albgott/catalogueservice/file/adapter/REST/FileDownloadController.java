package com.albgott.catalogueservice.file.adapter.REST;

import com.albgott.catalogueservice.file.application.download.DownloadFileQuery;
import com.albgott.catalogueservice.file.application.download.DownloadFileResponse;
import com.albgott.catalogueservice.file.application.download.DownloadFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FileDownloadController {
    private final DownloadFileService downloadFileService;

    public FileDownloadController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<?> getImageByName(@PathVariable String id){
        DownloadFileResponse image = downloadFileService.exec(new DownloadFileQuery(UUID.fromString(id)));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.type()))
                .body(image.data());
    }
}
