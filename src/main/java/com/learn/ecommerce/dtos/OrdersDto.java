package com.learn.ecommerce.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersDto {

	private Integer orderId;
    private String customerId;
    private Double totalAmount;
    private String orderStatus;
    private String shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean status;
}
