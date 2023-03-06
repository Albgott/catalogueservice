package com.albgott.catalogueservice.file.application.upload;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.file.domain.repository.FileRepository;
import com.albgott.catalogueservice.file.domain.service.ImageService;
import com.albgott.catalogueservice.shared.application.CommandUseCase;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService extends CommandUseCase<UploadFileCommand> {

    private final FileRepository repository;
    private final ImageService imageService;

    public UploadFileService(FileRepository repository, ImageService imageService) {
        this.repository = repository;
        this.imageService = imageService;
    }

    @Override
    protected void doExec(UploadFileCommand command) {
        String fileType = command.file().getContentType();
        byte[] fileData = null;
        try {
            fileData = imageService.compressImage(command.file().getBytes());
        } catch (Exception ignored){}

        File file = new File(
                command.id(),
                command.businessId(),
                fileType,
                fileData
        );

        throwIfErrors(file.errors());

        repository.save(file);
    }
}
