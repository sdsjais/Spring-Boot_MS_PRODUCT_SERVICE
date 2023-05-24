package com.dailyCodeBuffer.productService.controller;

import com.dailyCodeBuffer.productService.entity.Product;
import com.dailyCodeBuffer.productService.model.ProductRequest;
import com.dailyCodeBuffer.productService.model.ProductResponse;
import com.dailyCodeBuffer.productService.service.ProductService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest ){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>>getProduct(){
        List<ProductResponse> list = productService.getProduct();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin')||hasAuthority('Customer')||hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse>getProductById(@PathVariable("id") long productId){
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam("quantity") long quantity){
        productService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
