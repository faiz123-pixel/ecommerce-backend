package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.enumes.ShippingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDto {

	private Integer id;
	private Orders orders;
	private String courierService;
	private String trackingNumber;
	private ShippingStatus shippingStatus;
	private double shippingCost;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
