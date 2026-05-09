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

import com.learn.ecommerce.dtos.CartsDto;
import com.learn.ecommerce.services.CartsService;

@RestController
@CrossOrigin
@RequestMapping("/carts")
public class CartsController {
	
	@Autowired
	private CartsService cartsService;
	
	@PostMapping
	public ResponseEntity<CartsDto> addCart(@RequestBody CartsDto cartsDto)
	{
		return ResponseEntity.ok(cartsService.addCart(cartsDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CartsDto> updateCart(@RequestBody CartsDto cartsDto,@PathVariable Integer id)
	{
		return ResponseEntity.ok(cartsService.updateCart(cartsDto, id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CartsDto> getCartById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(cartsService.getCartById(id));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<CartsDto>> getCartById(@PathVariable String id)
	{
		return ResponseEntity.ok(cartsService.getCartByUser(id));
	}
	
	@GetMapping
	public ResponseEntity<List<CartsDto>> getAllCarts()
	{
		return ResponseEntity.ok(cartsService.getAllCart());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable Integer id)
	{
		cartsService.deleteCart(id);
		return ResponseEntity.ok("Cart deleted");
	}

}
