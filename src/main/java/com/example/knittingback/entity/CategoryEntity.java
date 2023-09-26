package com.example.knittingback.entity;

import com.example.knittingback.model.Item;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    The GenerationType.IDENTITY strategy means that the database will automatically increment the value of the primary key each time a new record is inserted.
    private long id;
    private int number;
    private String image;
    private String name;

    //mapped by uses field :id_category from ItemEntity
    @OneToMany(mappedBy ="id_category")
    private List<ItemEntity> items;


}
