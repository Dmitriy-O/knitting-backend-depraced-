package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Set;

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
    private String image;
    private String description;
    @Column(precision = 13, scale = 4)

    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "id_category")
    //id category-name of the new field in DB
    private CategoryEntity id_category;
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    private Set<OrderEntity> collection;



}
