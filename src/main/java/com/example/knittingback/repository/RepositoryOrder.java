package com.example.knittingback.repository;

import com.example.knittingback.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryOrder extends JpaRepository<OrderEntity, Long> {
}
