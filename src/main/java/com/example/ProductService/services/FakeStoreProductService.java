package com.example.ProductService.services;

import com.example.ProductService.dtos.FakeStoreProductDto;
//import com.example.ProductService.exceptions.ProductNotFoundException;
import com.example.ProductService.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStore")
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