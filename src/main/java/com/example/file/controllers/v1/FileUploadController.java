package com.example.file.controllers.v1;

import com.example.file.domain.FileMetadata;
import com.example.file.services.FetchMetadataService;
import com.example.file.services.MetadataService;
import com.example.file.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/files")
public class FileUploadController {
    private final StorageService storageService;
    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }
    @Autowired
    private FetchMetadataService fetchMetadataService;
    @Autowired
    private MetadataService metadataService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        FileMetadata fileMetadata = fetchMetadataService.fetchMetadata(file);
        System.out.println(fileMetadata.getName());
        System.out.println(fileMetadata.getSize());
        System.out.println(fileMetadata.getUploadTime());
        metadataService.saveFileMetadata(fileMetadata);
        return file.getOriginalFilename();
    }
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> serveFileByName(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
