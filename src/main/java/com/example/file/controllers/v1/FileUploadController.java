package com.example.file.controllers.v1;

import com.example.file.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {
    String test = "aaa";
    private final StorageService storageService;
    @Autowired
    public FileUploadController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("test") MultipartFile file) {
        storageService.store(file);
        String filename = file.getOriginalFilename();
        return filename;
    }
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFileByName(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
