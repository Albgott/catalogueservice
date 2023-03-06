package com.albgott.catalogueservice.file.application.upload;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record UploadFileCommand(UUID id, UUID businessId, MultipartFile file) {
}
