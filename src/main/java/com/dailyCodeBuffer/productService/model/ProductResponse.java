package com.dailyCodeBuffer.productService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String productName;
    private long productId;
    private long productPrice;
    private long productQuantity;
}
