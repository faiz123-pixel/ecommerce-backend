package com.learn.ecommerce.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dtos.OrdersDto;
import com.learn.ecommerce.services.OrdersService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@PostMapping
	public ResponseEntity<OrdersDto> placeOrder(@RequestBody OrdersDto ordersDto)
	{
		OrdersDto placeOrder = ordersService.placeOrder(ordersDto);
		return ResponseEntity.ok(placeOrder);
	}
	
	@GetMapping
	public ResponseEntity<List<OrdersDto>> getAllOrders()
	{
		List<OrdersDto> allOrders = ordersService.getAllOrders();
		return ResponseEntity.ok(allOrders);
	}
	
	 @GetMapping("/status/{status}")
	    public ResponseEntity<List<OrdersDto>> getOrdersByStatus(@PathVariable String status) {
	        List<OrdersDto> orders = ordersService.getOrdersByStatus(status);
	        return ResponseEntity.ok(orders);
	    }


	    @PutMapping("/{orderId}/status")
	    public ResponseEntity<OrdersDto> updateOrderStatus(
	            @PathVariable Integer orderId,
	            @RequestParam String status) {

	        OrdersDto updatedOrder = ordersService.updateOrderStatus(orderId, status);
	        return ResponseEntity.ok(updatedOrder);
	    }

	    @DeleteMapping("/{orderId}")
	    public ResponseEntity<?> cancelOrder(@PathVariable Integer orderId) {
	        try {
	            ordersService.cancelOrder(orderId);

	            return ResponseEntity.ok(
	                    Map.of(
	                            "message", "Order cancelled successfully",
	                            "status", true
	                    )
	            );

	        } catch (RuntimeException ex) {
	            return ResponseEntity.badRequest().body(
	                    Map.of(
	                            "message", ex.getMessage(),
	                            "status", false
	                    )
	            );
	        }
	    }
	
	
}
