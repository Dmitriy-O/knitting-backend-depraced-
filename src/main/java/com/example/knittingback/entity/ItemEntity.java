package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    @Column(length=1000)
//    private String imagePath;
    private String description;
    @Column(precision = 13, scale = 2)

    private BigDecimal price;


    //id category-name of the new field in DB
//    @ManyToOne
//    @JoinColumn(name = "id_category")
//    private CategoryEntity id_category;
//    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
//    private Set<OrderEntity> collection;
//@Column(length = 1000)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private FileEntity image_ID;



}
