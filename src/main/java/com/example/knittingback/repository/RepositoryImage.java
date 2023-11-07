package com.example.knittingback.repository;

import com.example.knittingback.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository

public interface RepositoryImage extends JpaRepository<ImageEntity,Long> {
    Optional<ImageEntity> findById(long id);
}
