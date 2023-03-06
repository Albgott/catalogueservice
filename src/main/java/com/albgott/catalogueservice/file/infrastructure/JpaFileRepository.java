package com.albgott.catalogueservice.file.infrastructure;

import com.albgott.catalogueservice.file.domain.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaFileRepository extends JpaRepository<File, UUID> {
}
