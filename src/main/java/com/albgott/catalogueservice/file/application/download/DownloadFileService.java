package com.albgott.catalogueservice.file.application.download;

import com.albgott.catalogueservice.file.domain.model.File;
import com.albgott.catalogueservice.file.domain.repository.FileRepository;
import com.albgott.catalogueservice.shared.application.QueryUseCase;
import org.springframework.stereotype.Service;

@Service
public class DownloadFileService extends QueryUseCase<DownloadFileQuery, DownloadFileResponse> {

    private final FileRepository fileRepository;

    public DownloadFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    protected DownloadFileResponse doExec(DownloadFileQuery command) {
        File file = fileRepository.findById(command.id())
                .orElseThrow(() -> new RuntimeException("not found"));

        return new DownloadFileResponse(file);
    }
}
