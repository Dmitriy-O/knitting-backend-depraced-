package com.example.knittingback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@JsonIgnoreProperties(value = {"Fieldhandler","hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "imagePathes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;

//    @Lob
//    @Column(length=1000)
    private String filePath;
                                                                                    //which entity gives the column - "mapped by" property and the arg is field of desirable connections opponent with @OneToOnne or @OneToMany
    @OneToOne(mappedBy = "image_ID")
    private ItemEntity itemEntity;
}
