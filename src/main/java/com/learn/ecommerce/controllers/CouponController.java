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

import com.learn.ecommerce.dtos.CouponsDto;
import com.learn.ecommerce.services.CouponService;

@RestController
@CrossOrigin
@RequestMapping("/coupons")
public class CouponController {

	@Autowired 
	private CouponService couponService;
	
	@PostMapping
	public ResponseEntity<CouponsDto> addCoupon(@RequestBody CouponsDto couponsDto)
	{
		return ResponseEntity.ok(couponService.addCoupon(couponsDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CouponsDto> updateCoupon(@PathVariable Integer id,@RequestBody CouponsDto couponsDto)
	{
		return ResponseEntity.ok(couponService.updateCoupon(id, couponsDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCoupon(@PathVariable Integer id)
	{
		couponService.deleteCoupon(id);
		return ResponseEntity.ok("Coupon Deleted");
	}
	
	@GetMapping
	public ResponseEntity<List<CouponsDto>> getAllCoupons()
	{
		return ResponseEntity.ok(couponService.getAllCoupons());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CouponsDto> getCouponById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(couponService.getCouponById(id));
	}
	
	@GetMapping("/couponcode/{code}")
	public ResponseEntity<CouponsDto> getCouponById(@PathVariable String code)
	{
		return ResponseEntity.ok(couponService.getCouponByCode(code));
	}
	
	@PutMapping("/activate/{id}")
	public ResponseEntity<CouponsDto> activateCoupon(@PathVariable Integer id)
	{
		return ResponseEntity.ok(couponService.activateCoupon(id));
	}
	
	@PutMapping("/deactivate/{id}")
	public ResponseEntity<CouponsDto> deactivateCoupon(@PathVariable Integer id)
	{
		return ResponseEntity.ok(couponService.deactivateCoupon(id));
	}
	
	@PutMapping("/reduce-usage/{id}")
	public ResponseEntity<String> reduceUsage(@PathVariable Integer id)
	{
		couponService.reduceUsage(id);
		return ResponseEntity.ok("updated");
	}
	
}
