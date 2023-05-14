package com.dailyCodeBuffer.productService.service;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.exception.ProductServiceCustomException;
import com.dailyCodeBuffer.productService.model.ProductRequest;
import com.dailyCodeBuffer.productService.model.ProductResponse;
import com.dailyCodeBuffer.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProductResponse> getProduct(){
        List<Product> list =  productRepository.findAll();
        return list.stream().map((Product -> {
            ProductResponse productResponse = ProductResponse.builder()
                    .productPrice(Product.getPrice())
                    .productId(Product.getProductId())
                    .productQuantity(Product.getQuantity())
                    .productName(Product.getProductName())
                    .build();
            return productResponse;
        })).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("find Product with productId:{}",productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("Product not found for given id","PRODUCT_NOT_FOUND"));
        System.out.println(product);
        ProductResponse productResponse = ProductResponse
                .builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getPrice())
                .productQuantity(product.getQuantity())
                .build();
        return productResponse;

    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {} of Product Id : {}", quantity, productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException("Product not found", "PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity)throw new ProductServiceCustomException("Product Quantity is Less", "INSUFFICIENT_QUANTITY");
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product Quantity updated for productId : {}",productId);
    }
}
