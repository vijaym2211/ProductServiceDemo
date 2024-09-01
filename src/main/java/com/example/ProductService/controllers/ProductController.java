package com.example.ProductService.controllers;

//import com.example.ProductService.exceptions.ProductNotFoundException;

import com.example.ProductService.dtos.CreateProductRequestDto;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.example.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")  //added common part from @Getmapping
public class ProductController {
    @Autowired
    @Qualifier("dbImpl")
    private ProductService productService;

    // GET /products/{id}
//    @GetMapping("/products/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) throws ProductNotFoundException {
//        if( productId < 1 || productId > 20){
//            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
//        }
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
    }

    @PostMapping()
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto) {
        System.out.println(requestDto);
        // Add basic validation
//        if(requestDto.getName() == null){
//            throw new Exception();
//        }

        return productService.createProduct(requestDto.getName(), requestDto.getCategory(),
                requestDto.getDescription());
    }

    @GetMapping()
    public List<Product> getProductList(@RequestBody CreateProductRequestDto req){
        return productService.getProductList();
    }

    @GetMapping("/abc/{cate}")
    public List<Product> getByCate(@PathVariable("cate") String str){
        return productService.getByCate(str);
    }
    @DeleteMapping("/{id}")
    public void deleteByid(@PathVariable("id") long id){
        productService.deleteByid(id);
    }

    @PutMapping("/{id}")
    public Product updateById(@PathVariable("id") long id, @RequestBody CreateProductRequestDto requestDto){
        return productService.updateById(id,requestDto.getName(),
                requestDto.getCategory(), requestDto.getDescription());
    }

}