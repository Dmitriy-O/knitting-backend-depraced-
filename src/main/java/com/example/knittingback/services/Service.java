package com.example.knittingback.services;

import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.model.Category;
import com.example.knittingback.model.Item;

import java.util.List;
import java.util.Optional;

public interface Service {

    Category createCategory(Category category);
    Optional<CategoryEntity> getCategoryEntityByID(long categoryID);
    Item createItem(Item item);

    List<Category> getAllCategories();
}
