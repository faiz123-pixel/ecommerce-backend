package com.learn.ecommerce.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.learn.ecommerce.enumes.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private Integer totalAmount;
	
	@ManyToOne
	private User customerId;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	private String shippingAddress;

	@CreationTimestamp
    private LocalDateTime createdAt;

	@UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean status;
	

}
