package com.learn.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RazorpayOrderResponse {
    private String id;
    private String entity;
    private Integer amount;
    private String amount_paid;
    private String amount_due;
    private String currency;
    private String receipt;
    private String offer_id;
    private String status;
    private Integer attempts;
    private String created_at;
}