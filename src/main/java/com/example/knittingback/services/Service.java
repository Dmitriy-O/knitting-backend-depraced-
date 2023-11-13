package com.example.knittingback.services;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.model.*;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Service {

    Category createCategory(Category category);
    com.example.knittingback.entity.ItemEntity createItem(@RequestPart MultipartFile image, String description, String price, String name) throws IOException;
    Order createOrder(Order order);
    Client createClient(Client client);
    Optional<CategoryEntity> getCategoryEntityByID(long categoryID);


    com.example.knittingback.entity.FileEntity uploadImageService(MultipartFile image) throws IOException;
    FileEntity downloadImageService(long id) throws IOException;

    List<ItemEntity> get_All_Items();
    List<FileEntity> getAllImages();

    boolean deleteModel(long id);
}
