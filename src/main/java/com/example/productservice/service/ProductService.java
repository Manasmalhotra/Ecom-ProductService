package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO product);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProduct(UUID productId);
    Product getfullProduct(UUID productId);
    String deleteProduct(UUID productId);

    ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO productRequest);

    ProductResponseDTO findProductByTitle(String title);
}
