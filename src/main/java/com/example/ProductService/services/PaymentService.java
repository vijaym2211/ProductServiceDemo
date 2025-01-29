package com.example.ProductService.services;

public interface PaymentService {
    String createPaymentLink(long OrderId) throws Exception;
}
