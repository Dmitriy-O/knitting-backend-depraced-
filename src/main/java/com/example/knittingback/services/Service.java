package com.example.knittingback.services;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface Service {

    Category createCategory(Category category);
    Item createItem(Item item);
    Order createOrder(Order order);
    Client createClient(Client client);
    Optional<CategoryEntity> getCategoryEntityByID(long categoryID);


    String uploadImageService(MultipartFile image) throws IOException;
    ImageM downloadImageService(long id) throws IOException;

    List<Category> getAllCategories();
    List<ImageM> getAllImages();
}
