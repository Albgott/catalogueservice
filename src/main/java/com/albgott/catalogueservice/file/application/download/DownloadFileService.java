package com.albgott.catalogueservice.file.application.download;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.file.domain.repository.FileRepository;
import com.albgott.catalogueservice.file.domain.service.ImageService;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import com.albgott.catalogueservice.shared.domain.exception.AppError;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class DownloadFileService extends QueryUseCase<DownloadFileQuery, DownloadFileResponse> {

    private final ImageService imageService;
    private final FileRepository fileRepository;

    public DownloadFileService(ImageService imageService, FileRepository fileRepository) {
        this.imageService = imageService;
        this.fileRepository = fileRepository;
    }

    @Override
    protected DownloadFileResponse doExec(DownloadFileQuery command) {
        Optional<File> fileOptional = fileRepository.findById(command.id());
        if(fileOptional.isEmpty()) {
            throwIfErrors(new HashSet<>(){{add(new AppError("file.not_found"));}});
        }

        File file = fileOptional.get();
        return new DownloadFileResponse(
                new File(file.id(),file.businessId(),file.type(), imageService.decompressImage(file.data()))
        );
    }
}
