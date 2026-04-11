package com.example.productService.service;


import com.example.productService.model.Product;
import com.example.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
