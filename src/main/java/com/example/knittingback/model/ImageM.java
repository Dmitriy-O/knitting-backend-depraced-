package com.example.knittingback.model;

import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ImageM {
    private long id;
    private String name;
    private String type;
    private String filePath;


    public Image convertToImageFormat(String filePath){
       Image image=Toolkit.getDefaultToolkit().createImage(filePath);
       return image;
    }
}
