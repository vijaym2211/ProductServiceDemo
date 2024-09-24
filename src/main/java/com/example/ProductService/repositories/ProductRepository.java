package com.example.ProductService.repositories;

import com.example.ProductService.models.Product;
import com.example.ProductService.projections.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

//Types of Queries
//
//        Declare Queries
//        HQL(Hibernate Query Language)
//        SQL - native = true

//Connecting to DB
@Repository
//extend JpaRepository with Product(Entity) and Primary key type(long)
public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from products where name = {name} and category = {category}
    Product findFirstByNameAndCategory(String name, String category);

    // Find all products
    // select * from products
    List<Product> findAll();

    // Find product by Id
    // select * from products where id = {id}
//    Product findById(long id);
    /*Optional<Product> is a container object in Java that may or may not contain a non-null value of type Product.
    It’s part of the java.util package and was introduced in Java 8 to help avoid NullPointerExceptions
    and to promote more functional programming practices.*/
    Optional<Product> findById(long id);

//    Declare query
//    Optional<Product> findById(long id);

    // Find all products by category
    // select * from products where category = {category}
    List<Product> findAllByCategory(String category);

    void deleteById(Long aLong);

//    Declare Query
    List<Product> findAllByNameLike(String word);

    List<Product> findAllByNameLikeIgnoreCase(String word);

    List<Product> findAllByIdLessThan(long id);

//    HQL
    @Query("select p.name, p.category from Product p where p.id = :id")
    List<Product> selectById(long id);

//    SQL
    @Query(value = "select * from product p where p.id =:id", nativeQuery = true)
    Product selectById2(long id);

    //For Projection
    @Query(nativeQuery = true,
            value = "select p.id, p.name, p.description as descp from product p where id=:id")
    ProductInfo getProductInfo(long id);

    Page<Product> findAll(Pageable pageable);
}