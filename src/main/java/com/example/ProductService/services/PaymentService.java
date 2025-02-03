package com.example.ProductService.services;

import com.example.ProductService.models.Order;

public interface PaymentService {
    String createPaymentLink(long OrderId) throws Exception;
    void updateOrderStatusAfterPayment(Order order);
}
