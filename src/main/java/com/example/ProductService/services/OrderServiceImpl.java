package com.example.ProductService.services;

import com.example.ProductService.exception.OrderNotFoundException;
import com.example.ProductService.exception.ProductNotFoundException;
import com.example.ProductService.models.Order;
import com.example.ProductService.models.OrderStatus;
import com.example.ProductService.models.Product;
import com.example.ProductService.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductServiceImpl productService;

    public Order createOrder(long productId, long userId, Long quantity, OrderStatus status) throws ProductNotFoundException {
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
        //again in stripe we multiplying with quantity
        newOrder.setTotalPrice(product.getPrice() * quantity);
//        newOrder.setTotalPrice(product.getPrice());
        newOrder.setStatus(status != null ? status : OrderStatus.PENDING);

        return orderRepository.save(newOrder);
    }


    public Order getOrderById(long orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isPresent()) {
            return order.get();
        }
        else{
            throw new OrderNotFoundException("Order with id:" + orderId + " is not available");
        }
    }
}
