package com.example.knittingback.services;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.knittingback.converter.CategoryToCategoryEntity;
import com.example.knittingback.entity.CategoryEntity;
import com.example.knittingback.entity.ItemEntity;
import com.example.knittingback.model.Category;
import com.example.knittingback.model.Item;
import com.example.knittingback.repository.RepositoryCategory;
import com.example.knittingback.repository.RepositoryEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private RepositoryCategory repositoryCategory;

    private RepositoryEntity repositoryEntity;

    public ServiceImpl(RepositoryCategory repositoryCategory, RepositoryEntity repositoryEntity) {
        this.repositoryCategory = repositoryCategory;
        this.repositoryEntity = repositoryEntity;
    }

    //Конструктор класса ServiceImpl необходим, потому что он является единственным способом передать экземпляр класса Repository в класс ServiceImpl. Если вы не используете конструктор, вам нужно будет вручную внедрить экземпляр класса Repository в класс ServiceImpl. Это может быть утомительно и подвержено ошибкам.
    @Override
    public Category createCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(category, categoryEntity);
        repositoryCategory.save(categoryEntity);
        return category;
    }

//    @Override
//    public CategoryEntity getCategoryEntityByID(long categoryID) {
//        CategoryEntity referenceById = repositoryCategory.getReferenceById(categoryID);
//        return referenceById;
//    }

    @Override
    public Item createItem(Item item) {
        CategoryToCategoryEntity converter = new CategoryToCategoryEntity();
        ItemEntity itemEntity = ItemEntity.builder()
                .name(item.getName())
                .image(item.getImage())
                .description(item.getDescription())
                .id_category(converter.convert(item.getId_category()))
                .build();
//        BeanUtils.copyProperties(item,itemEntity);
//        itemEntity.setId(item.getId());
//        itemEntity.setName(item.getName());
//        itemEntity.setImage(item.getImage());
//        itemEntity.setDescription(item.getDescription());
//        I
        repositoryEntity.save(itemEntity);
        return item;
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
//
/**
 * Модель и сущность - это два важных понятия в объектно-ориентированном программировании. Они часто используются в контексте базы данных.
 * <p>
 * Сущность - это объект, который представляет запись в таблице базы данных. Сущности имеют набор атрибутов, которые соответствуют столбцам в таблице базы данных.
 * <p>
 * Модель - это представление сущности. Модель может содержать дополнительные данные, которые не хранятся в базе данных. Например, модель может содержать данные, необходимые для отображения сущности в пользовательском интерфейсе.
 * В целом, модели и сущности используются для разных целей. Сущности используются для доступа к данным в базе данных. Модели используются для представления этих данных в приложении.
 * <p>
 * Вот несколько дополнительных различий между моделями и сущностями:
 * <p>
 * Модели обычно являются более сложными, чем сущности. Они могут содержать дополнительные данные, которые не хранятся в базе данных.
 * Модели обычно используются для отображения данных в пользовательском интерфейсе. Они могут содержать методы, которые используются для отображения данных в пользовательском интерфейсе.
 * Сущности обычно используются для доступа к данным в базе данных. Они могут содержать методы, которые используются для доступа к данным в базе данных.
 */

