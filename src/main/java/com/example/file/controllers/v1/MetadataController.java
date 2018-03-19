package com.example.file.controllers.v1;

import com.example.file.domain.FileMetadata;
import com.example.file.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/metadata")
public class MetadataController {
    @Autowired
    MetadataService metadataService;
    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getMetadataById(@PathVariable Long id){
        return new ResponseEntity<>(metadataService.getFileMetadataById(id), HttpStatus.OK);
    }
    @GetMapping("/recent")
    public ResponseEntity<List<FileMetadata>> getLastHourMetadata(){
        return new ResponseEntity<>(metadataService.getLastHourMetadata(), HttpStatus.OK);
    }
}
