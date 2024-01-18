package com.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ProductRequestDTO {
    private String title;
    private String description;
    private String image;
    private double price;
    private double discount;
    private String category;
    private int totalAvailableQuantity;
}
