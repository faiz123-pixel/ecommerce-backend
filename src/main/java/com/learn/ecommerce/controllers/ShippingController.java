package com.learn.ecommerce.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dtos.ShippingDto;
import com.learn.ecommerce.services.ShippingService;

@RestController
@CrossOrigin
@RequestMapping("/shipping")
public class ShippingController {


	@Autowired
	private ShippingService shippingService;

	
	@PostMapping
	public ResponseEntity<ShippingDto> addShipping(@RequestBody ShippingDto shippingDto)
	{
		ShippingDto shipping = shippingService.addShipping(shippingDto);
		return ResponseEntity.ok(shipping);
	}
	
	@GetMapping
	public ResponseEntity<List<ShippingDto>> getAllShipping()
	{
		List<ShippingDto> allShipping = shippingService.getAllShipping();
		return ResponseEntity.ok(allShipping);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ShippingDto> updateShipping(@PathVariable Integer id,@RequestBody ShippingDto shippingDto)
	{
		ShippingDto updateShipping = shippingService.updateShipping(id, shippingDto);
		return ResponseEntity.ok(updateShipping);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShipping(@PathVariable Integer id)
	{
		shippingService.deleteShipping(id);
		return ResponseEntity.ok("Shipping details deleted");
	}
	
}
