package com.dailyCodeBuffer.productService.service;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.model.ProductRequest;
import com.dailyCodeBuffer.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest){
        log.info("Adding Product...");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product Created");
        return product.getProductId();
    }

    @Override
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
}
