package com.learn.ecommerce.services.impl;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.learn.ecommerce.dtos.PaymentVerificationRequest;
import com.learn.ecommerce.dtos.RazorpayOrderRequest;
import com.learn.ecommerce.dtos.RazorpayOrderResponse;
import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.entities.Payments;
import com.learn.ecommerce.enumes.PaymentStatus;
import com.learn.ecommerce.repositories.OrdersRepository;
import com.learn.ecommerce.repositories.PaymentsRepository;
import com.razorpay.Order;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Service
public class RazorpayService {

    private RazorpayClient razorpayClient;

    @Value("${Razorpay.key}")
    private String razorpayKey;

    @Value("${Razorpay.secret}")
    private String razorpaySecret;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @PostConstruct
    public void init() throws RazorpayException {
        System.out.println("Initializing Razorpay client with key: " + razorpayKey);
        this.razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);
        System.out.println("Razorpay client initialized successfully");
    }

    public RazorpayOrderResponse createOrder(RazorpayOrderRequest orderRequest) throws RazorpayException {
        try {
            JSONObject orderRequestJson = new JSONObject();
            orderRequestJson.put("amount", orderRequest.getAmount());
            orderRequestJson.put("currency", orderRequest.getCurrency());
            orderRequestJson.put("receipt", orderRequest.getReceipt() != null ? orderRequest.getReceipt() : "rcpt" + UUID.randomUUID().toString());
            orderRequestJson.put("payment_capture", 1);

            Order order = razorpayClient.orders.create(orderRequestJson);

            RazorpayOrderResponse response = new RazorpayOrderResponse();
            response.setId(order.get("id").toString());
            response.setEntity(order.get("entity").toString());
            response.setAmount(Integer.parseInt(order.get("amount").toString()));
            response.setAmount_paid(order.get("amount_paid").toString());
            response.setAmount_due(order.get("amount_due").toString());
            response.setCurrency(order.get("currency").toString());
            response.setReceipt(order.get("receipt").toString());
            response.setOffer_id(order.get("offer_id") != null ? order.get("offer_id").toString() : null);
            response.setStatus(order.get("status").toString());
            response.setAttempts(Integer.parseInt(order.get("attempts").toString()));
            response.setCreated_at(order.get("created_at").toString());

            return response;
        } catch (RazorpayException e) {
            throw new RazorpayException("Failed to create Razorpay order: " + e.getMessage());
        }
    }

    public boolean verifyPayment(PaymentVerificationRequest verificationRequest) {
        try {
            String payload = verificationRequest.getRazorpay_order_id() + "|" + verificationRequest.getRazorpay_payment_id();
            String generatedSignature = calculateHmacSha256(payload, razorpaySecret);
            
            System.out.println("Payment Verification Debug:");
            System.out.println("Order ID: " + verificationRequest.getRazorpay_order_id());
            System.out.println("Payment ID: " + verificationRequest.getRazorpay_payment_id());
            System.out.println("Received Signature: " + verificationRequest.getRazorpay_signature());
            System.out.println("Generated Signature: " + generatedSignature);
            System.out.println("Payload: " + payload);
            
            boolean isValid = generatedSignature.equals(verificationRequest.getRazorpay_signature());
            System.out.println("Signature Match: " + isValid);
            
            // If verification is successful, save payment data
            if (isValid) {
                savePaymentData(verificationRequest);
                System.out.println("Payment data saved successfully");
            }
            
            return isValid;
        } catch (Exception e) {
            System.err.println("Payment verification error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Payment verification failed: " + e.getMessage());
        }
    }

    private void savePaymentData(PaymentVerificationRequest verificationRequest) {
        try {
            Payments payment = new Payments();
            payment.setPaymentMethod("RAZOR");
            payment.setPaymentStatus(PaymentStatus.PAID);
            payment.setRazorpayPaymentId(verificationRequest.getRazorpay_payment_id());
            
            // Link payment to order if orderId is provided
            if (verificationRequest.getOrderId() != null) {
                Orders order = ordersRepository.findById(verificationRequest.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found with ID: " + verificationRequest.getOrderId()));
                payment.setOrder(order);
                payment.setAmount(order.getTotalAmount());
                System.out.println("Payment linked to order ID: " + verificationRequest.getOrderId());
            }
            
            paymentsRepository.save(payment);
            System.out.println("Payment saved with transaction ID: " + verificationRequest.getRazorpay_payment_id());
        } catch (Exception e) {
            System.err.println("Error saving payment data: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save payment data: " + e.getMessage());
        }
    }

    private String calculateHmacSha256(String data, String secret) throws Exception {
        javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256");
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        byte[] result = mac.doFinal(data.getBytes());
        
        // Convert to hexadecimal string to match Razorpay's format
        StringBuilder hexString = new StringBuilder();
        for (byte b : result) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
