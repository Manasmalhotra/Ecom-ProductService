package com.example.productservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(name="Category")
@Getter
@Setter
public class Category extends BaseModel{
    String categoryName;
}
