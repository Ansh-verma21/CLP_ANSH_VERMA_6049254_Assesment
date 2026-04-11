package com.example.orderService.controller;


import com.example.orderService.dto.OrderRequest;
import com.example.orderService.dto.OrderResponse;
import com.example.orderService.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    @CircuitBreaker(name = "moviedetailservice", fallbackMethod = "fallbackCreateOrder")
    public OrderResponse createOrder(@Valid @RequestBody OrderRequest request) {
        return service.createOrder(request);
    }

    public OrderResponse fallbackCreateOrder(@Valid @RequestBody OrderRequest request, Exception e) {
    	
    	OrderResponse r = new OrderResponse(0L,"Unavailab","No product Service",0,0);
        
        return r;
    }
    
}
