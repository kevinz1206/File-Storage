package com.example.file.repositories;

import com.example.file.domain.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileMetadataRepository extends JpaRepository<FileMetadata,Long>{
    @Query(value = "select * from File_Metadata f WHERE DATEDIFF('second', f.upload_time, CURRENT_TIMESTAMP) < 3600", nativeQuery = true)
    List<FileMetadata> findLastHourMetadata();
}
