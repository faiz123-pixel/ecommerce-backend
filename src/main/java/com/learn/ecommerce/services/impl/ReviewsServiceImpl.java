package com.learn.ecommerce.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.ReviewsDto;
import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.Reviews;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.repositories.ProductRepository;
import com.learn.ecommerce.repositories.ReviewsRepository;
import com.learn.ecommerce.repositories.UserRepository;
import com.learn.ecommerce.services.ReviewsService;

@Service
public class ReviewsServiceImpl implements ReviewsService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ReviewsRepository reviewsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public ReviewsDto addReview(ReviewsDto reviewsDto) {

	    Reviews reviews = new Reviews();

	    reviews.setRating(reviewsDto.getRating());
	    reviews.setReviewText(reviewsDto.getReviewText());
	    reviews.setStatus(true);

	    reviews.setProduct(reviewsDto.getProduct());

	    reviews.setUser(reviewsDto.getUser());

	    // Save Review
	    Reviews savedReview = reviewsRepository.save(reviews);

	    

	    return modelMapper.map(savedReview, ReviewsDto.class);
	}

	@Override
	public ReviewsDto updateReview(Integer id, ReviewsDto reviewsDto) {
		Reviews reviews = reviewsRepository.findById(id).orElseThrow(()->new RuntimeException("Review not found"));
		reviews.setRating(reviewsDto.getRating());
		reviews.setReviewText(reviewsDto.getReviewText());
		reviews.setStatus(reviewsDto.getStatus());
		return modelMapper.map(reviewsRepository.save(reviews), ReviewsDto.class);
	}

	@Override
	public void deleteReview(Integer id) {
		Reviews reviews = reviewsRepository.findById(id).orElseThrow(()->new RuntimeException("Review not found"));
		reviewsRepository.delete(reviews);
		
	}

	@Override
	public List<ReviewsDto> getAllReviews() {
		List<Reviews> list = reviewsRepository.findAll();
		return list.stream().map((r)->modelMapper.map(r, ReviewsDto.class)).toList();
	}
	
	@Override
	public List<ReviewsDto> getReviewByUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		List<Reviews> list = reviewsRepository.findByUser(user);
		return list.stream().map((r)->modelMapper.map(r, ReviewsDto.class)).toList();
	}

	@Override
	public List<ReviewsDto> getReviewByProduct(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
		List<Reviews> list = reviewsRepository.findByProduct(product);
		return list.stream().map((r)->modelMapper.map(r, ReviewsDto.class)).toList();
	}

	@Override
	public ReviewsDto getReviewById(Integer id) {
		Reviews reviews = reviewsRepository.findById(id).orElseThrow(()->new RuntimeException("Review not found"));
		return modelMapper.map(reviews, ReviewsDto.class);
	}

}
