package com.example.file.services;

import com.example.file.domain.FileMetadata;
import com.example.file.properties.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class FetchMetadataService {
    @Autowired
    StorageProperties storageProperties;

    public FileMetadata fetchMetadata(MultipartFile multipartFile){
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setName(multipartFile.getOriginalFilename());
        fileMetadata.setSize(multipartFile.getSize());
        fileMetadata.setUploadTime(new Date());
        return fileMetadata;
    }
}
