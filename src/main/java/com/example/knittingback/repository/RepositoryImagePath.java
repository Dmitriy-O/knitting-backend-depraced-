package com.example.knittingback.repository;

import com.example.knittingback.entity.FileEntity;
import com.example.knittingback.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@org.springframework.stereotype.Repository

public interface RepositoryImagePath extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findById(long id);
    Optional<FileEntity> findByName (String name);
    FileEntity findByFilePath (String path);
    FileEntity findTopByName(String name);


}
