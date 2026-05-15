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
public class ReviewsDto {

	private Integer id;
	private User user;
	private Product product;
	private Integer rating;
	private String reviewText;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean status;
}
