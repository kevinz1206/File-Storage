package com.example.file.services;


import com.example.file.domain.FileMetadata;

import java.util.List;

public interface MetadataService {

    void saveFileMetadata(FileMetadata fileMetadata);

    FileMetadata getFileMetadataById(Long id);

    List<FileMetadata> getLastHourMetadata();
}
