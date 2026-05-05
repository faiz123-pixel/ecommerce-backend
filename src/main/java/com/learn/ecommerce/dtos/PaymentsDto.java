package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.enumes.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsDto {
	
	private Integer paymentId;
	private String razorpayPaymentId;
	private Orders order;
	private double amount;
	private String paymentMethod;
	private PaymentStatus paymentStatus;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
