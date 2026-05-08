package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartsDto {

	private Integer cartId;
	private Product product;
	private User user;
	private Integer quantity;
	private double totalPrice;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
