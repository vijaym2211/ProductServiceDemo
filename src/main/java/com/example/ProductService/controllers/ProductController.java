package com.example.ProductService.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/hello")
    public String helloworld(){
        return "Hello World";
    }
}
