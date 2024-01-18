package com.example.productservice.service;

import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.exception.InvalidTitleException;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;


public class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);// creates auto closeable resources for each test method
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings={"testTitle"})
    public void testFindProductByTitle(String title){
        Product product=new Product();
        Price price=new Price();
        price.setAmount(1000);
        Category category=new Category();
        category.setCategoryName("Laptop");
        product.setTitle(title);
        product.setImage("xyz");
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription("");
        if(title==null || title.isEmpty()){
            when(productRepository.findByTitle(title)).thenReturn(product);
            Assertions.assertThrows(InvalidTitleException.class,()->productService.findProductByTitle(title));
        }
        else {
            when(productRepository.findByTitle(title)).thenReturn(product);
            ProductResponseDTO actualResponse = productService.findProductByTitle(title);
            Assertions.assertEquals(product.getId(), actualResponse.getId());
            Assertions.assertEquals(product.getTitle(), actualResponse.getTitle());
            Assertions.assertEquals(product.getPrice().getAmount(), actualResponse.getPrice());
            Assertions.assertEquals(product.getDescription(), actualResponse.getDescription());
        }
    }

    @Test
    public void testFindByProductByTitle_RepoRespondsWithNullObject() throws ResourceNotFoundException {
        //Arrange
        String testTitle = "testProductTitle";
        when(productRepository.findByTitle(testTitle)).thenReturn(null);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.findProductByTitle(testTitle));
    }

}