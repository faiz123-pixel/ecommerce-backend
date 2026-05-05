package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.PaymentsDto;

public interface PaymentsService {
	
	PaymentsDto addPayment(PaymentsDto paymentsDto);
	PaymentsDto updatePayment(PaymentsDto paymentsDto, Integer id);
	PaymentsDto getPaymentById(Integer id);
	List<PaymentsDto> getAllPayments();
	public void deletePayment(Integer id);
	String refundPayment(Integer paymentId);
}
