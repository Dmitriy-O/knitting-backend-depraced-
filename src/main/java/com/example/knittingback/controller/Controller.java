package com.example.knittingback.controller;

import com.example.knittingback.entity.Client_ItemEntity;
import com.example.knittingback.model.Category;
import com.example.knittingback.model.Client;
import com.example.knittingback.model.Item;
import com.example.knittingback.model.Order;
import com.example.knittingback.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping("/client_items")

    public  Order showTable(@RequestBody Order order) {
        return service.showTableDB(order);
    }

    @PostMapping("/items")
    public Item createCategory(@RequestBody Item item) {
        return service.createItem(item);
    }
    @PostMapping("/order")
    public Order createOrder() {
        Order order=new Order().builder().date(LocalDateTime.now()).build();
        return service.createOrder(order);
    }
    @PostMapping("/client")
    public Client createClient(@RequestBody Client client) {
        return service.createClient(client);
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
