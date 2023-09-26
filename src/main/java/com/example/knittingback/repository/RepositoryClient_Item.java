package com.example.knittingback.repository;

import com.example.knittingback.entity.Client_ItemEntity;
import com.example.knittingback.entity.Client_ItemEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryClient_Item extends JpaRepository<Client_ItemEntity, Client_ItemEntityKey> {
}
