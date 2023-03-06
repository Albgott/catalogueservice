package com.albgott.catalogueservice.file.infrastructure;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.file.domain.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class FileRepositoryImpl implements FileRepository {
    private final JpaFileRepository repository;

    public FileRepositoryImpl(JpaFileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(File file) {
        repository.save(file);
    }

    @Override
    public Optional<File> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(File file) {
        repository.delete(file);
    }
}
