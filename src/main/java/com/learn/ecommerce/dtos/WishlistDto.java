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
public class WishlistDto {
	
	private Integer id;
	private User user;
	private Product  product;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
