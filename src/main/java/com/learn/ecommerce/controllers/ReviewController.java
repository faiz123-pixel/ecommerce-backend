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

import com.learn.ecommerce.dtos.ReviewsDto;
import com.learn.ecommerce.services.ReviewsService;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewsService reviewsService;
	
	@PostMapping
	public ResponseEntity<ReviewsDto> addReview(@RequestBody ReviewsDto reviewsDto)
	{
		return ResponseEntity.ok(reviewsService.addReview(reviewsDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReviewsDto> updateReview(@PathVariable Integer id,@RequestBody ReviewsDto reviewsDto)
	{
		return ResponseEntity.ok(reviewsService.updateReview(id,reviewsDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable Integer id)
	{
		reviewsService.deleteReview(id);
		return ResponseEntity.ok("Review Deleted");
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<ReviewsDto>> getReviewByUser(@PathVariable String id)
	{
		return ResponseEntity.ok(reviewsService.getReviewByUser(id));
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<ReviewsDto>> getReviewByProduct(@PathVariable Integer id)
	{
		return ResponseEntity.ok(reviewsService.getReviewByProduct(id));
	}
	
	@GetMapping()
	public ResponseEntity<List<ReviewsDto>> getAllReviews()
	{
		return ResponseEntity.ok(reviewsService.getAllReviews());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReviewsDto> getReviewById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(reviewsService.getReviewById(id));
	}
	
	

}
