package com.example.ProductService.dtos;


import com.example.ProductService.models.OrderStatus;
import lombok.Data;

@Data
public class CreateOrderRequestDto {
    private Long productId;
    private Long userId;
    private int quantity;
    private OrderStatus status;

}
