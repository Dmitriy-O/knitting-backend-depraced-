package com.example.knittingback.model;

import com.example.knittingback.entity.ItemEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

//these data are used in postmen request !!
@Getter
@Setter
//@Builder
@NoArgsConstructor
public class Category {
    private long id;
    private int number;
    private String image;
    private String name;
    private List<Item> items;

    public Category(long id, int number, String image, String name) {
        this.id = id;
        this.number = number;
        this.image = image;
        this.name = name;
    }
}
