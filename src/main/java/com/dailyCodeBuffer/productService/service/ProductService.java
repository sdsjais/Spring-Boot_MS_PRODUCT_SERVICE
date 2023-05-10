package com.dailyCodeBuffer.productService.service;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.model.ProductRequest;
import com.dailyCodeBuffer.productService.model.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public long addProduct(ProductRequest productRequest);
    public List<ProductResponse> getProduct();

    public ProductResponse getProductById(long productId);
}
