package com.example.ProductService.services;

import com.example.ProductService.models.Product;

public interface ProductService {
    com.example.ProductService.models.Product getProductById(long productId);
}
