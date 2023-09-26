package com.example.knittingback.services;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.model.Category;
import com.example.knittingback.model.Client;
import com.example.knittingback.model.Item;
import com.example.knittingback.model.Order;

import java.util.List;
import java.util.Optional;

public interface Service {

    Category createCategory(Category category);
    Item createItem(Item item);
    Order createOrder(Order order);
    Client createClient(Client client);
    Order showTableDB(Order clientItemEntity);
    Optional<CategoryEntity> getCategoryEntityByID(long categoryID);

    List<Category> getAllCategories();

}
