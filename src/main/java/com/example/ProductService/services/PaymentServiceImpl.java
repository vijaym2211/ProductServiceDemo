package com.example.ProductService.services;

import com.example.ProductService.Payment_Gateway.PaymentGatewayInterface;
import com.example.ProductService.models.Order;
import com.example.ProductService.models.OrderStatus;
import com.example.ProductService.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentGatewayInterface pgi;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createPaymentLink(long orderId) throws Exception {
        //Call Get /orders/{orderId} and fetch order info

        // Fetch order info from the repository
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            throw new Exception("Order not found for ID: " + orderId);
        }

        Order order = optionalOrder.get(); // Get the actual Order object

        // Now call the update method to update the order status
        updateOrderStatusAfterPayment(order);

        return pgi.createPaymentLink(order);
    }


    public void updateOrderStatusAfterPayment(Order order){
        order.setStatus(OrderStatus.CONFIRMED);
        orderRepository.save(order);
    }
}
