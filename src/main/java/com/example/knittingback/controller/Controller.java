package com.example.knittingback.controller;

import com.example.knittingback.model.Category;
import com.example.knittingback.model.Item;
import com.example.knittingback.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//The @RestController annotation tells Spring Boot that this class is a REST controller. This means that it will handle HTTP requests and return responses in JSON format.
@RequestMapping("/api/v1")
//The @RequestMapping("/api/v1") annotation specifies the base URL for all requests handled by this controller. In this case, the base URL is /api/v1.
public class Controller {

    @Autowired
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
    public Item createCategory(@RequestBody Item item) {
//        int id_Category= (int) item.getId_category().getId();
//        Item forCreation=new Item(item.getId(), item.getName(), item.getImage(),  item.getDescription(), )
        return service.createItem(item);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return service.getAllCategories();
    }
    @GetMapping("/items")
    public List<Category> getAllItems(){
        return service.getAllCategories();
    }
}