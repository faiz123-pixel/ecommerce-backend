package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.WishlistDto;

public interface WishlistService {

	WishlistDto addWishlist(WishlistDto wishlistDto);
	WishlistDto updateWishlist(Integer id,WishlistDto wishlistDto);
	void deleteWishlist(Integer id);
	WishlistDto getWishlistById(Integer id);
	List<WishlistDto> getWishlistByUser(String id);
	List<WishlistDto> getAllWishlist();
	
	
}
