package com.dailyCodeBuffer.productService.service;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.model.ProductRequest;

import java.util.List;

public interface ProductService {
    public long addProduct(ProductRequest productRequest);
    public List<Product> getProduct();
}
