package com.example.ProductService.projections;

//The easiest way to limit the result of the queries to only the name attributes
//is by declaring an interface that exposes accessor methods for the properties to be read.
//if we want BIG data can't visible to us with the help of projection ex:- content varchar big data
public interface ProductInfo {
    long getId();

    String getName();

    String getDescp();
}