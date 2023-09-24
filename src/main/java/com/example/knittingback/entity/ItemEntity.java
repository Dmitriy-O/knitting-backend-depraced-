package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String image;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_category")
    //id category-name of the new field in DB
    private CategoryEntity id_category;


}
