package com.learn.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.ecommerce.dtos.PaymentVerificationRequest;
import com.learn.ecommerce.dtos.PaymentsDto;
import com.learn.ecommerce.dtos.RazorpayOrderRequest;
import com.learn.ecommerce.dtos.RazorpayOrderResponse;
import com.learn.ecommerce.services.PaymentsService;
import com.learn.ecommerce.services.impl.RazorpayService;

@RestController
@RequestMapping("/payments")
@CrossOrigin
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @Autowired
    private RazorpayService razorpayService;

    @PostMapping
    public ResponseEntity<PaymentsDto> addPayment(@RequestBody PaymentsDto paymentsDto) {

        PaymentsDto savedPayment = paymentsService.addPayment(paymentsDto);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentsDto> getPaymentById(@PathVariable Integer id) {

        return ResponseEntity.ok(paymentsService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentsDto>> getAllPayments() {

        return ResponseEntity.ok(paymentsService.getAllPayments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentsDto> updatePayment(
            @RequestBody PaymentsDto paymentsDto,
            @PathVariable Integer id) {

        return ResponseEntity.ok(paymentsService.updatePayment(paymentsDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePayment(@PathVariable Integer id) {

        paymentsService.deletePayment(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Payment deleted successfully");
        response.put("status", "success");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-order")
    public ResponseEntity<RazorpayOrderResponse> createOrder(@RequestBody RazorpayOrderRequest orderRequest) {
        try {
            System.out.println("Creating order with amount: " + orderRequest.getAmount());
            RazorpayOrderResponse orderResponse = razorpayService.createOrder(orderRequest);
            System.out.println("Order created successfully: " + orderResponse.getId());
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            System.err.println("Error creating Razorpay order: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyPayment(@RequestBody PaymentVerificationRequest verificationRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean isValid = razorpayService.verifyPayment(verificationRequest);
            response.put("success", isValid);
            response.put("message", isValid ? "Payment verified successfully" : "Payment verification failed");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Payment verification error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/refund/{id}")
    public String refundPayment(@PathVariable("id") Integer paymentId)
    {
    	return paymentsService.refundPayment(paymentId);
    }
}

