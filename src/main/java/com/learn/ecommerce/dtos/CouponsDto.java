package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import com.learn.ecommerce.enumes.CouponDiscountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponsDto {

	private Integer id;
	private String couponCode;
	private CouponDiscountType couponDiscountType;
	private double discountValue;
	private LocalDateTime validFrom;
	private LocalDateTime validTo;
	private Integer usageLimit;
	private boolean status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
