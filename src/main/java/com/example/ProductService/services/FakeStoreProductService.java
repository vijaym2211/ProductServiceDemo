package com.example.ProductService.services;

import com.example.ProductService.dtos.FakeStoreProductDto;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStore")
public class FakeStoreProductService implements ProductService{

    @Autowired //Due to this @autowired RestTemplate object inserted here
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        /*
        Take the id from the input, and call this endpoint:a
        https://fakestoreapi.com/products/ + id
         */
        /*
        To install docker use this:
        docker run --name my-redis -d -p 6379:6379 redis
         */
        Product product =(Product) this.redisTemplate.opsForHash().get("PRODUCTS", "product_" + id);
        if(product != null){
            return product;
        }
        String url = "https://fakestoreapi.com/products/" + id;
//        RestTemplate restTemplate = new RestTemplate();      // this going to call multiple times
//        FakeStoreProductDto fakeSDto = restTemplate.getForObject(url, FakeStoreProductDto.class);
        FakeStoreProductDto fakeSDto = this.restTemplate.getForObject(url, FakeStoreProductDto.class);
        if(fakeSDto == null){
            throw new ProductNotFoundException("Product with id:" + id + " was not found");
        }
        product = convertFakeStoreProductToProduct(fakeSDto);
        this.redisTemplate.opsForHash().put("PRODUCTS", "product_" + id, product);
        return product;
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public void deleteByid(long id) {
    }

    @Override
    public List<Product> getByCate(String category) {
        return null;
    }

    @Override
    public Product updateById(long id, String name, String category, String description) {
        return null;
    }

    @Override
    public Product createProduct(String name, String category, String description) {
        return null;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto dto){
        //to get data from fakestore and set inside the product
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNum, String sortBy){
        return null;
    }
}