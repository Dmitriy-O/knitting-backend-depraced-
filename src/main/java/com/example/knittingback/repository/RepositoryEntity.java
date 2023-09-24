package com.example.knittingback.repository;

import com.example.knittingback.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * T: The type of the entity that the repository will manage.
 * ID: The type of the identifier for the entity.
 */
public interface RepositoryEntity extends JpaRepository<ItemEntity,Long> {
}
