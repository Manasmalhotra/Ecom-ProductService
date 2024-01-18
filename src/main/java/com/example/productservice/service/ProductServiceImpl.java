package com.example.productservice.service;

import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.dto.ProductResponseDTO;
import com.example.productservice.exception.InvalidTitleException;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Price;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    CategoryService categoryService;
    PriceService priceService;

    public ProductServiceImpl(ProductRepository productRepository,CategoryService categoryService,PriceService priceService){
        this.productRepository=productRepository;
        this.categoryService=categoryService;
        this.priceService=priceService;
    }

    private ProductResponseDTO mapProductToResponse(Product product){
        ProductResponseDTO responseDTO=new ProductResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setPrice(product.getPrice().getAmount());
        responseDTO.setImage(product.getImage());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setDiscount(product.getPrice().getDiscountPercentage());
        responseDTO.setCategory(product.getCategory().getCategoryName());
        responseDTO.setTitle(product.getTitle());
        return responseDTO;
    }

    private Product mapRequestToProduct(ProductRequestDTO productRequestDTO){
        Product product=new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setImage(productRequestDTO.getImage());
        product.setDescription(productRequestDTO.getDescription());
        Price price=priceService.createPrice(new Price(productRequestDTO.getPrice(), productRequestDTO.getDiscount()));
        product.setPrice(price);
        Category category= categoryService.getCategoryByName(productRequestDTO.getCategory());
        if(category==null){
            category=categoryService.createCategory(productRequestDTO.getCategory());
        }
        product.setCategory(category);
        return product;
    }

    @Override
    public Product getfullProduct(UUID productId) {
        return productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product","ID",productId.toString()));
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO product) {
        Product p=mapRequestToProduct(product);
        productRepository.save(p);
        return mapProductToResponse(p);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapProductToResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) {
        Product product=getfullProduct(productId);
        return mapProductToResponse(product);
    }

    @Override
    public String deleteProduct(UUID productId) {
        Product product=getfullProduct(productId);
        productRepository.delete(product);
        return "Product Deleted Successfully!!";
    }

    @Override
    public ProductResponseDTO updateProduct(UUID productId,ProductRequestDTO productRequest) {
        Product product=getfullProduct(productId);
        product.setCategory(categoryService.getCategoryByName(productRequest.getCategory()));
        product.setDescription(productRequest.getDescription());
        product.setTitle(productRequest.getTitle());
        product.setImage(productRequest.getImage());
        product.setPrice(Price.builder()
                .amount(productRequest.getPrice())
                .discountPercentage(productRequest.getDiscount())
                .build());
        productRepository.save(product);
        return mapProductToResponse(product);
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        if(title==null || title.isEmpty()){
            throw new InvalidTitleException();
        }
        Product product=productRepository.findByTitle(title);
        if(product==null){
            throw new ResourceNotFoundException("Product","Title",title);
        }

        return mapProductToResponse(product);
    }
}
