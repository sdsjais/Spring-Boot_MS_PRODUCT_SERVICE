package com.dailyCodeBuffer.productService.controller;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.model.ProductRequest;
import com.dailyCodeBuffer.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest ){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>>getProduct(){
        List<Product> list = productService.getProduct();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
