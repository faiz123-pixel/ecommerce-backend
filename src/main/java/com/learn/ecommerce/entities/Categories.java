package com.learn.ecommerce.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	
	@Column(length = 100, nullable = false)
	private String categoryName;
	
	@Column(length = 300)
	private String description;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@CreationTimestamp
	private LocalDateTime updatedAt;
	
	private Boolean status;
}
