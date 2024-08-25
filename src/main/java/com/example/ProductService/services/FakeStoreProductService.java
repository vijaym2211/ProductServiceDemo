package com.example.ProductService.services;

import com.example.ProductService.dtos.FakeStoreProductDto;
//import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService{

    @Override
    public Product getProductById(long id){
        /*
        Take the id from the input, and call this endpoint:a
        https://fakestoreapi.com/products/ + id
         */
        String url = "https://fakestoreapi.com/products/" + id;
        RestTemplate restTemplate = new RestTemplate();      // used for 1 time call
        FakeStoreProductDto fakeSDto = restTemplate.getForObject(url, FakeStoreProductDto.class);
//        if(fakeStoreProductDto == null){
//            throw new ProductNotFoundException("Product with id:" + id + " was not found");
//        }
        return convertFakeStoreProductToProduct(fakeSDto);
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto dto){
        //to get data from fakestore and set inside the product
        Product product = new Product();
        product.setCategory(dto.getCategory());
        product.setDescription(dto.getDescription());
        product.setName(dto.getTitle());
        return product;
    }
}