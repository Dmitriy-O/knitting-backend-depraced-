package com.example.knittingback.services;

import com.example.knittingback.converter.CategoryToCategoryEntity;
import com.example.knittingback.entity.*;
import com.example.knittingback.model.*;
import com.example.knittingback.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

//@JacksonFeatures(serializationDisable = {SerializationFeature.FAIL_ON_EMPTY_BEANS})

public class ServiceImpl implements Service {

    final private RepositoryCategory repositoryCategory;
    final private RepositoryEntity repositoryEntity;
    final private RepositoryOrder repositoryOrder;
    final private RepositoryClient repositoryClient;
    final private RepositoryImage repositoryImage;
    final private RepositoryImagePath repositoryImagePath;

    final private String FOLDER_PATH = "D:\\Projects\\Portfolio\\full-stack\\images\\";

    public ServiceImpl(RepositoryCategory repositoryCategory, RepositoryEntity repositoryEntity, RepositoryOrder repositoryOrder, RepositoryClient repositoryClient, RepositoryImage repositoryImage, RepositoryImagePath repositoryImagePath) {
        this.repositoryCategory = repositoryCategory;
        this.repositoryEntity = repositoryEntity;
        this.repositoryOrder = repositoryOrder;
        this.repositoryClient = repositoryClient;
        this.repositoryImage = repositoryImage;
        this.repositoryImagePath = repositoryImagePath;
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

        ClientEntity clientEntity = ClientEntity.builder()
                .name(client.getName())
                .build();
        repositoryClient.save(clientEntity);
        return client;
    }


    @Override
    public String uploadImageService(MultipartFile image) throws IOException {
        String filePath = "D:\\Projects\\Portfolio\\full-stack\\images\\" + image.getOriginalFilename();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        image.transferTo(Path.of(filePath));
        if (!image.isEmpty()) {
            return "file uploaded successfully : " + filePath;
        }
        FileEntity fileEntity = repositoryImagePath.save(FileEntity.builder()
                .name(image.getOriginalFilename())
                .type(image.getContentType())
                .filePath(filePath)
                .build());
        return null;
    }

    //    @Override
//    public BufferedImage downloadImageService(long id) throws IOException {
//        Optional<FileData> dbImageData = repositoryImagePath.findById(id);
//        String filePath = dbImageData.get().getFilePath();
//        byte[] image = Files.readAllBytes(new File(filePath).toPath());
//        BufferedImage resultImage= ImageIO.read(new File(filePath));
//        return resultImage;
//    }
    @Override
    public ImageM downloadImageService(long id) throws IOException {
        Optional<FileEntity> dbImageData = repositoryImagePath.findById(id);
//    Image image =new Image();
//    FileData fileData=new FileData();
//        dbImageData.get().
        ImageM image = ImageM
                .builder()
                .id(dbImageData.get().getId())
                .name(dbImageData.get().getName())
                .type(dbImageData.get().getType())
                .filePath(dbImageData.get().getFilePath())
                .build();

        String filePath = dbImageData.get().getFilePath();
//        byte[] image = Files.readAllBytes(new File(filePath).toPath());
//        BufferedImage resultImage = ImageIO.read(new File(filePath));
        return image;
    }
//    @Configuration
//    public class JacksonConfig {
//
//        @Bean
//        public ObjectMapper objectMapper() {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//            return objectMapper;
//        }
//    }


//    @Override
//    public byte[] downloadImageService(long id) throws IOException {
//        Optional<ImageEntity> dbImageData = repositoryImage.findById(id);
//        byte[] image = ImageUtils.decompressImage(dbImageData.get().getData());
//        return image;
//
//    }

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
    public List<ImageM> getAllImages() {
        List<FileEntity> fileData = repositoryImagePath.findAll();
        List<ImageM> imageList = fileData
                .stream()
                .map(photo -> ImageM.builder()
                        .id(photo.getId())
                        .name(photo.getName())
                        .type(photo.getType())
                        .filePath(photo.getFilePath())
                        .build()).toList();
        return imageList;
    }

    @Override
    public Optional<CategoryEntity> getCategoryEntityByID(long categoryID) {

        Optional<CategoryEntity> categoryById = repositoryCategory.findById(categoryID);
        return categoryById;
    }
}

