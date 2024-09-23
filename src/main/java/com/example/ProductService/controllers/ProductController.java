package com.example.ProductService.controllers;

//import com.example.ProductService.exceptions.ProductNotFoundException;

import com.example.ProductService.dtos.CreateProductRequestDto;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.example.ProductService.services.ProductService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/AllProducts")
    public List<Product> getProductList(){ // error you’re encountering indicates that your getProductList
        // method is expecting a request body, but none is being provided when you hit the endpoint.
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
    public Product updateById(@PathVariable("id") long id, @RequestBody CreateProductRequestDto requestDto) throws ProductNotFoundException{
        return productService.updateById(id,requestDto.getName(),
                requestDto.getCategory(), requestDto.getDescription());
    }

    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(
        @RequestParam(value = "pageSize", defaultValue = "2") int pageSize,
        @RequestParam(value = "pageNum", defaultValue = "0") int pageNum
        ){
        Page<Product> products= productService.getAllProducts(pageSize, pageNum);
        return ResponseEntity.ok(products);
    }


}