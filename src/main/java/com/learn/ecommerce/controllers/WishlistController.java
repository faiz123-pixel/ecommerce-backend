package com.learn.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.ecommerce.dtos.WishlistDto;
import com.learn.ecommerce.services.WishlistService;

@RestController
@CrossOrigin
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@PostMapping
	public ResponseEntity<WishlistDto> addWishlist(@RequestBody WishlistDto wishlistDto)
	{
		WishlistDto wishlist = wishlistService.addWishlist(wishlistDto);
		return ResponseEntity.ok(wishlist);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteWishlist(@PathVariable Integer id)
	{
		wishlistService.deleteWishlist(id);
		return ResponseEntity.ok("Wishlist clear");
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable String id)
	{
		List<WishlistDto> list = wishlistService.getWishlistByUser(id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping
	public ResponseEntity<List<WishlistDto>> getAllWishlist()
	{
		List<WishlistDto> list = wishlistService.getAllWishlist();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WishlistDto> getWishlistById(@PathVariable Integer id)
	{
		WishlistDto wishlist = wishlistService.getWishlistById(id);
		return ResponseEntity.ok(wishlist);
	}
	

}
