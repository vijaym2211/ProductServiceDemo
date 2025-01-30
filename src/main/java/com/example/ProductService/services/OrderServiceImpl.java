package com.example.ProductService.services;

import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Order;
import com.example.ProductService.models.OrderStatus;
import com.example.ProductService.models.Product;
import com.example.ProductService.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceImpl productService;

    public Order createOrder(long productId, long userId, int quantity, OrderStatus status) throws ProductNotFoundException {
        // Check product availability
        Product product = productService.getProductById(productId);
        if(product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        Order newOrder = new Order();
        newOrder.setProductId(product.getId());
        newOrder.setQuantity(quantity);
        newOrder.setUserId(userId);

        // Calculate total price
        newOrder.setTotalPrice(product.getPrice() * quantity);
        newOrder.setStatus(status != null ? status : OrderStatus.PENDING);

        return orderRepository.save(newOrder);
    }

}
