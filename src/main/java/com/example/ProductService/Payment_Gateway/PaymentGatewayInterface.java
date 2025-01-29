package com.example.ProductService.Payment_Gateway;

import com.stripe.exception.StripeException;

public interface PaymentGatewayInterface {

    public String createPaymentLink(long orderId, long amount) throws Exception;
}
