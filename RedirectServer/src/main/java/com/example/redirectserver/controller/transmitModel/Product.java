package com.example.redirectserver.controller.transmitModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    String name;
    String description;
    String price;
    String image;
    String category_name;
    String category_description;
}
