package com.example.ProductService.services;

import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Product;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
//    com.example.ProductService.models.Product getProductById(long productId);
    public Product getProductById(long id) throws ProductNotFoundException;

    public Product createProduct(String name, String category, String description);

    public List<Product> getProductList();
    public void deleteByid(long id);

    public List<Product> getByCate(String category);

    public Product updateById(long id, String name, String category, String description) throws ProductNotFoundException;;

    public Page<Product> getAllProducts(int pageSize, int pageNum);
}
