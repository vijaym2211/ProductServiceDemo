package com.example.ProductService.services;

import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Product;
import com.example.ProductService.projections.ProductInfo;
import com.example.ProductService.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        ProductInfo productInfo = productRepository.getProductInfo(id);
        System.out.println(productInfo.getDescp());
        System.out.println(productInfo.getName());
        System.out.println(productInfo.getId());
        return productRepository.findById(id);
    }

//    with this we get description as "Product with id:4 is not available" if id 4 not available
//    @Override
//    public Product getProductById(long id) throws ProductNotFoundException {
////        return null;
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if(optionalProduct.isPresent()){
//            return optionalProduct.get();
//        }
//        else {
//            throw new ProductNotFoundException("Product with id:" + id + " is not available");
//        }
//    }


    @Override
    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String category, String description) {
        /*
        select * from products where name = {name}
        if the above query returns a product, then do not create a new product
         */
        Product p = productRepository.findFirstByNameAndCategory(name, category);
        if(p != null){
            return p;
        }
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(description);
        product.setName(name);
        product = productRepository.save(product);
        System.out.println(product.getId());
        return product;
    }
    @Override
    public List<Product> getByCate(String category){
        return productRepository.findAllByCategory(category);
    }
    @Override
    public void deleteByid(long id){
        productRepository.deleteById(id);
    }

    public Product updateById(long id, String name, String category, String description){

        Product p = productRepository.findById(id);
        if(p == null){
            throw new RuntimeException();
        }

        p.setCategory(category);
        p.setDescription(description);
        p.setName(name);
        p = productRepository.save(p);
        System.out.println(p.getId());
        return p;
    }
}