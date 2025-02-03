package com.example.ProductService.dtos;


import com.example.ProductService.models.OrderStatus;
import lombok.Data;

@Data
public class CreateOrderResponseDto {
    private Long productId;
    private Long userId;
    private Long quantity;
    private double totalPrice;
    private OrderStatus status;
}
