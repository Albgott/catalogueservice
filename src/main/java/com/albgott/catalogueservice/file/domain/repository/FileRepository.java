package com.albgott.catalogueservice.file.domain.repository;

import com.albgott.catalogueservice.file.domain.model.File;

import java.util.Optional;
import java.util.UUID;

public interface FileRepository {
    void save(File file);
    Optional<File> findById(UUID id);
    void deleteById(UUID id);
    void delete(File file);
}
