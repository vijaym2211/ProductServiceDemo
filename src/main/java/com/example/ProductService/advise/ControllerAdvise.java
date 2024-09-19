package com.example.ProductService.advise;

import com.example.ProductService.exception.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Help ProductNotFoundException to represent in proper format
@ControllerAdvice
public class ControllerAdvise {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> getProductNotFoundException(ProductNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(400));
    }
}