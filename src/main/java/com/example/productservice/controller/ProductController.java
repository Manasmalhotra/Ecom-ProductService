package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    ResponseEntity<ProductResponseDTO>addProduct(@RequestBody ProductRequestDTO product){
        return new ResponseEntity(productService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductResponseDTO>getProduct(@PathVariable String id){
        UUID productId=UUID.fromString(id);
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/title/{title}")
    ResponseEntity<ProductResponseDTO> findProductByTitle(@PathVariable String title){
        return ResponseEntity.ok(productService.findProductByTitle(title));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable String id){
        UUID productId=UUID.fromString(id);
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id,@RequestBody ProductRequestDTO productRequest){
        UUID productId=UUID.fromString(id);
        return ResponseEntity.ok(productService.updateProduct(productId,productRequest));
    }
}
