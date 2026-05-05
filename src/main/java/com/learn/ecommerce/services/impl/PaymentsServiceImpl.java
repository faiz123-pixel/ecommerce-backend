package com.learn.ecommerce.services.impl;

import java.util.List;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.PaymentsDto;
import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.entities.Payments;
import com.learn.ecommerce.enumes.OrderStatus;
import com.learn.ecommerce.enumes.PaymentStatus;
import com.learn.ecommerce.repositories.OrdersRepository;
import com.learn.ecommerce.repositories.PaymentsRepository;
import com.learn.ecommerce.services.PaymentsService;
import com.razorpay.RazorpayClient;

@Service
public class PaymentsServiceImpl implements PaymentsService {

	
    @Autowired
    private PaymentsRepository paymentsRepository;
    
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private RazorpayClient razorpayClient;

    @Override
    public PaymentsDto addPayment(PaymentsDto paymentsDto) {

        Payments payment = modelMapper.map(paymentsDto, Payments.class);
        Payments savedPayment = paymentsRepository.save(payment);

        return modelMapper.map(savedPayment, PaymentsDto.class);
    }

    @Override
    public PaymentsDto updatePayment(PaymentsDto paymentsDto, Integer id) {


        return null;
    }

    @Override
    public PaymentsDto getPaymentById(Integer id) {

        Payments payment = paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return modelMapper.map(payment, PaymentsDto.class);
    }

    @Override
    public List<PaymentsDto> getAllPayments() {

        List<Payments> payments = paymentsRepository.findAll();

        return payments.stream()
                .map(payment -> modelMapper.map(payment, PaymentsDto.class))
                .toList();
    }

    @Override
    public void deletePayment(Integer id) {

        Payments payment = paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentsRepository.delete(payment);
    }

	@Override
	public String refundPayment(Integer paymentId) {
		try
		{
			Payments payment = paymentsRepository.findById(paymentId)
			        .orElseThrow(() -> new RuntimeException("Payment not found"));
			Orders orders = ordersRepository.findById(payment.getOrder().getOrderId()).orElseThrow(()->new RuntimeException("order not found"));

			if (payment.getPaymentStatus() != PaymentStatus.PAID) {
			    throw new RuntimeException("Refund not allowed");
			}
			if (orders.getOrderStatus() != OrderStatus.CANCELLED) {
			    throw new RuntimeException("Refund allowed only for cancelled orders");
			}

			String razorpayPaymentId = payment.getRazorpayPaymentId();

			com.razorpay.Payment razorpayPayment =
			        razorpayClient.payments.fetch(razorpayPaymentId);

			int amountPaid = razorpayPayment.get("amount");
			int amountRefunded = razorpayPayment.get("amount_refunded");

			int remainingAmount = amountPaid - amountRefunded;

			if (remainingAmount <= 0) {
			    throw new RuntimeException("Already fully refunded");
			}

			JSONObject refundRequest = new JSONObject();
			refundRequest.put("amount", remainingAmount);

		
			razorpayClient.payments.refund(razorpayPaymentId, refundRequest);

			payment.setPaymentStatus(PaymentStatus.REFUNDED);
			paymentsRepository.save(payment);
			
			return "Refund successful";
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Refund failed");
		}
	}
}


