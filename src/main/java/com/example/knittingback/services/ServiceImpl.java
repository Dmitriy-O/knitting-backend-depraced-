package com.example.knittingback.services;

import com.example.knittingback.converter.CategoryToCategoryEntity;
import com.example.knittingback.converter.OrderToOrderEntity;
import com.example.knittingback.entity.*;
import com.example.knittingback.model.Category;
import com.example.knittingback.model.Client;
import com.example.knittingback.model.Item;
import com.example.knittingback.model.Order;
import com.example.knittingback.repository.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    final private RepositoryCategory repositoryCategory;
    final private RepositoryEntity repositoryEntity;
    final private RepositoryOrder repositoryOrder;
    final private RepositoryClient repositoryClient;

    public ServiceImpl(RepositoryCategory repositoryCategory, RepositoryEntity repositoryEntity, RepositoryOrder repositoryOrder, RepositoryClient repositoryClient) {
        this.repositoryCategory = repositoryCategory;
        this.repositoryEntity = repositoryEntity;
        this.repositoryOrder = repositoryOrder;
        this.repositoryClient = repositoryClient;
    }

    //Конструктор класса ServiceImpl необходим, потому что он является единственным способом передать экземпляр класса Repository в класс ServiceImpl. Если вы не используете конструктор, вам нужно будет вручную внедрить экземпляр класса Repository в класс ServiceImpl. Это может быть утомительно и подвержено ошибкам.
    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        repositoryCategory.save(categoryEntity);
        return category;
    }


    @Override
    public Item createItem(Item item) {
        CategoryToCategoryEntity converter = new CategoryToCategoryEntity();
        ItemEntity itemEntity = ItemEntity.builder()
                .name(item.getName())
                .image(item.getImage())
                .description(item.getDescription())
                .id_category(converter.convert(item.getId_category()))
                .build();
        repositoryEntity.save(itemEntity);
        return item;
    }

    @Override
    public Order createOrder(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId())
                .date(order.getDate()).build();
        repositoryOrder.save(orderEntity);
        return order;
    }

    @Override
    public Client createClient(Client client) {

        ClientEntity clientEntity = new ClientEntity().builder()
                .name(client.getName())
                .build();
        repositoryClient.save(clientEntity);
        return client;
    }



    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities
                = repositoryCategory.findAll();
        //Список CategoryEntity содержит объекты класса CategoryEntity, которые представляют записи в таблице categories в базе данных
        List<Category> categories = categoryEntities
                .stream().map
                        (categ -> new Category(
                                categ.getId(),
                                categ.getNumber(),
                                categ.getImage(),
                                categ.getName())).toList();
        //Список Category содержит объекты класса Category, которые представляют представления этих записей.
        return categories;
    }


    @Override
    public Optional<CategoryEntity> getCategoryEntityByID(long categoryID) {

        Optional<CategoryEntity> categoryById = repositoryCategory.findById(categoryID);
        return categoryById;
    }
}

