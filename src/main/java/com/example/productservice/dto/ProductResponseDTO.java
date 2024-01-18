package com.example.productservice.dto;

import com.example.productservice.model.Category;
import com.example.productservice.model.Price;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private double price;
    private double discount;
    private String category;
    private int totalAvailableQuantity;
}
