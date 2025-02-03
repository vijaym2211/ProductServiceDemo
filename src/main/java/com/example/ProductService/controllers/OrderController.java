package com.example.ProductService.controllers;

import com.example.ProductService.dtos.CreateOrderRequestDto;
import com.example.ProductService.dtos.CreateOrderResponseDto;
import com.example.ProductService.exception.OrderNotFoundException;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Order;
import com.example.ProductService.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<CreateOrderResponseDto> bookProduct(@RequestBody CreateOrderRequestDto createOrderRequestDto) throws ProductNotFoundException {
        Order savedOrder = orderService.createOrder(createOrderRequestDto.getProductId(),
                            createOrderRequestDto.getUserId(),
                            createOrderRequestDto.getQuantity(),
                            createOrderRequestDto.getStatus());

        CreateOrderResponseDto createOrderResponseDto = maptoResponseDto(savedOrder);
        return new ResponseEntity<>(createOrderResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CreateOrderResponseDto> getOrderById(@PathVariable Long orderId) throws ProductNotFoundException, OrderNotFoundException {
        Order order = orderService.getOrderById(orderId);
        CreateOrderResponseDto OrderResponseDto = maptoResponseDto(order);
        return new ResponseEntity<>(OrderResponseDto, HttpStatusCode.valueOf(200));
    }

    public CreateOrderResponseDto maptoResponseDto(Order savedOrder) {
        CreateOrderResponseDto responseDto = new CreateOrderResponseDto();
        responseDto.setProductId(savedOrder.getProductId());
        responseDto.setUserId(savedOrder.getUserId());
        responseDto.setQuantity(savedOrder.getQuantity());
        responseDto.setTotalPrice(savedOrder.getTotalPrice());
        responseDto.setStatus(savedOrder.getStatus());
        return responseDto;
    }
}
