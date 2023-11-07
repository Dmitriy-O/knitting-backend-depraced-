package com.example.knittingback.controller;

import com.example.knittingback.model.*;
import com.example.knittingback.model.ImageM;
import com.example.knittingback.services.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/// @Controller, @Repository, @Service are beans
@org.springframework.stereotype.Controller
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//The @RestController annotation tells Spring Boot that this class is a REST controller. This means that it will handle HTTP requests and return responses in JSON format.
@RequestMapping("/api/v1")
//The @RequestMapping("/api/v1") annotation specifies the base URL for all requests handled by this controller. In this case, the base URL is /api/v1.
public class Controller {

    //    @Autowired
    //The @Autowired annotation tells Spring Boot to inject the Service dependency into the Controller class. The Service class is responsible for interacting with the database.
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    //The public Controller(Service service) constructor injects the Service dependency into the Controller class.
    @PostMapping("/categories")
    //The @PostMapping("/categories") annotation specifies that the createCategory() method will handle HTTP POST requests to the /categories URL.
    //The public Category createCategory(@RequestBody Category category) method creates a new category and returns it. The @RequestBody annotation tells Spring Boot to bind the JSON data in the request body to the category parameter.
    public Category createCategory(@RequestBody Category category) {
        return service.createCategory(category);
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        return service.createItem(item);
    }

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        try {
            String uploadImageService = service.uploadImageService(image);
            return ResponseEntity.status(HttpStatus.OK).body(uploadImageService);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        @Bean
        public HttpMessageConverter<BufferedImage> bufferedImageHttpMessageConverter() {
            return new BufferedImageHttpMessageConverter();
        }
    @GetMapping("/images/{id}")
    public BufferedImage downloadImage(@PathVariable("id") long id) {
        try {
            ImageM imageData = service.downloadImageService(id);
//            ImageUtils.decompressImage(imageData);

            List<MediaType> supportedMediaTypes = Arrays.asList(
                    MediaType.valueOf("image/png"),
                    MediaType.valueOf("image/jpg")
            );
//            Image body = (imageData).convertToImageFormat(imageData.getFilePath());
            BufferedImage bufferedImage = ImageIO.read(new File(imageData.getFilePath()));

return bufferedImage;
//            return ResponseEntity.ok()
//
//
//                    .contentType(MediaType.parseMediaType(String.valueOf(imageData)))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,"image;filename=\""+imageData.getName()+"\"")
//                    .body(bufferedImage);
//                        return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .contentType(MediaType.valueOf("image/png"))
//                    .contentType(MediaType.valueOf("image/jpg"))
//                    .body(imageData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//            MediaType mediaType = MediaType.parseMediaTypes(supportedMediaTypes).stream()
//                    .filter(type -> type.isCompatibleWith(MediaType.valueOf(imageType)))
//                    .findFirst().orElse(MediaType.APPLICATION_OCTET_STREAM);

//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .contentType(MediaType.valueOf("image/png"))
//                    .contentType(MediaType.valueOf("image/jpg"))
//                    .body(imageData);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        @PostMapping("/images/{id}")
//    public String uploadImage(@RequestParam("image")MultipartFile file,RequestParam("id") long id) throws IOException {
//        try {
//            return service.uploadImageService(file);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @PostMapping("/images/{id}")
//    public byte[] downloadImage(@RequestBody MultipartFile file,) throws IOException {
//        try {
//            return service.downloadImageService("file");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @PostMapping("/order")
    public Order createOrder() {
        Order order = new Order().builder().date(LocalDateTime.now()).build();
        return service.createOrder(order);
    }

    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {
        return service.createClient(client);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/items")
    public List<Category> getAllItems() {
        return service.getAllCategories();
    }

    @GetMapping("/images")
    public List<ImageM> downloadAllimages() {
        return service.getAllImages();

    }
}
