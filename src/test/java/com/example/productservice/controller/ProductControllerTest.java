package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.service.ProductServiceImplTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean(name="productService")
    ProductService productService;

    private String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Test
    public void getAllProductsReturnsEmptyList() throws Exception {
        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products")).andExpect(status().is(200))
                .andExpect(content().string("[]"));
    }

    @Test
    public void getAllProducts() throws Exception {
        ProductResponseDTO product=new ProductResponseDTO();
        product.setId(UUID.randomUUID());
        product.setTitle("Asus Laptop");
        product.setImage("hello.jpg");
        product.setPrice(1000);
        product.setDiscount(20);
        product.setCategory("Laptop");
        product.setDescription("Best laptop");
        List<ProductResponseDTO> products=List.of(product);
        Mockito.when(productService.getAllProducts()).thenReturn(products);
        String jsonObj=convertToJson(products);
        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string(jsonObj));
    }

    @Test
    public void productCreationSuccess() throws Exception {
        ProductRequestDTO productRequestDTO=new ProductRequestDTO();
        productRequestDTO.setTitle("Xyz");
        productRequestDTO.setDiscount(20);
        productRequestDTO.setDescription("Xyz");
        productRequestDTO.setPrice(1000);
        productRequestDTO.setImage("Hello.png");
        productRequestDTO.setCategory("Electronics");

        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setDescription(productRequestDTO.getDescription());
        productResponseDTO.setId(UUID.randomUUID());
        productResponseDTO.setDiscount(productRequestDTO.getDiscount());
        productResponseDTO.setPrice(productRequestDTO.getPrice());
        productResponseDTO.setCategory(productRequestDTO.getCategory());
        productResponseDTO.setImage(productRequestDTO.getImage());
        productResponseDTO.setTitle(productRequestDTO.getTitle());

        Mockito.when(productService.addProduct(eq(productRequestDTO))).thenReturn(productResponseDTO);

        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(productRequestDTO)))
                .andExpect(status().is(201))
                .andExpect(content().string(convertToJson(productResponseDTO)));
    }
}
