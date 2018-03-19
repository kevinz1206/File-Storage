package com.example.file.services;

import com.example.file.domain.FileMetadata;
import com.example.file.repositories.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    FileMetadataRepository fileMetadataRepository;
    @Override
    public void saveFileMetadata(FileMetadata fileMetadata) {
        fileMetadataRepository.save(fileMetadata);
    }

    @Override
    public FileMetadata getFileMetadataById(Long id) {
       return fileMetadataRepository.findById(id).get();
    }

    @Override
    public List<FileMetadata> getLastHourMetadata() {
        return fileMetadataRepository.findLastHourMetadata();
    }
}
