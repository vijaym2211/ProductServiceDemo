package com.example.ProductService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProductControllerDemo {
    @GetMapping("/hello")
    public String helloworld(){
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String hellowithname(@PathVariable("name") String name){
        return "Hello " + name;
    }

    // GET /show/{showId}/seat/{seatId}
    @GetMapping("/show/{showId}/seat/{seatId}")
    public String bmsExample(@PathVariable("showId") String showId, @PathVariable("seatId") int seatId){
        return "Hello " + showId + " " + seatId;
    }
}
