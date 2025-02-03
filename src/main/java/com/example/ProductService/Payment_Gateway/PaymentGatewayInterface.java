package com.example.ProductService.Payment_Gateway;

import com.example.ProductService.models.Order;

import java.util.Optional;

public interface PaymentGatewayInterface {

    public String createPaymentLink(Order order) throws Exception;
}
