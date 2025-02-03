package com.example.ProductService.services;

import com.example.ProductService.exception.OrderNotFoundException;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Order;
import com.example.ProductService.models.OrderStatus;

public interface OrderService {
    public Order createOrder(long productId, long userId, Long quantity, OrderStatus status) throws ProductNotFoundException;

    public Order getOrderById(long orderId) throws OrderNotFoundException;
}
