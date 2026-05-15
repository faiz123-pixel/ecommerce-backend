package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.ReviewsDto;

public interface ReviewsService {

	ReviewsDto addReview(ReviewsDto reviewsDto);
	ReviewsDto updateReview(Integer id,ReviewsDto reviewsDto);
	void deleteReview(Integer id);
	List<ReviewsDto> getAllReviews();
	List<ReviewsDto> getReviewByUser(String id);
	List<ReviewsDto> getReviewByProduct(Integer id);
	ReviewsDto getReviewById(Integer id);

}
