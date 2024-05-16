package com.example.hw1;

import lombok.Data;


@Data
public class Product {
    private Long id;
    private String imageUrl;
    private String productName;
    private double price;
    private int stock;
}
