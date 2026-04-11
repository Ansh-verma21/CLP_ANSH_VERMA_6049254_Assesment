package com.example.orderService.dto;

import jakarta.validation.constraints.Min;

public class OrderRequest {

    private Long userId;
    private Long productId;

    @Min(value = 1, message = "Quantity must be positive")
    private int quantity;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
}