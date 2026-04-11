package com.example.orderService.service;

import com.example.orderService.dto.OrderRequest;
import com.example.orderService.dto.OrderResponse;
import com.example.orderService.dto.Product;
import com.example.orderService.dto.User;
import com.example.orderService.exception.ProductNotFoundException;
import com.example.orderService.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

   
    private long count = 5000;
    
    

    public OrderResponse createOrder(OrderRequest request) {

        User user = restTemplate.getForObject("http://userService/users/" + request.getUserId(), User.class);

        if (user == null) {
        	
            throw new UserNotFoundException("User not found having id " + request.getUserId());
        }

        Product product = restTemplate.getForObject("http://productService/products/" + request.getProductId(), Product.class);

        if (product == null) {
        	
            throw new ProductNotFoundException("Product not found having id " + request.getProductId());
        }

        double totalPrice = product.getPrice() * request.getQuantity();
        count++;

        return new OrderResponse(count, user.getName(), product.getName(), request.getQuantity(), totalPrice);
    }
    
    

    
}