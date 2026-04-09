package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import com.learn.ecommerce.entities.Categories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
	
	private Integer productId;
	private String productName;
	private String description;
	private double price;
	private String SKU;
	private Categories categoryId;
	private Integer inventoryCount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean status;

}
