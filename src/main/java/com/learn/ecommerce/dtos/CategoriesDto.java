package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriesDto {
	
	private int categoryId;
	private String categoryName;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean status;

}
