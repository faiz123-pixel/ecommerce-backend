package com.learn.ecommerce.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.WishlistDto;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.entities.Wishlist;
import com.learn.ecommerce.repositories.UserRepository;
import com.learn.ecommerce.repositories.WishlistRepository;
import com.learn.ecommerce.services.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public WishlistDto addWishlist(WishlistDto wishlistDto) {
		
		Wishlist wishlist = modelMapper.map(wishlistDto, Wishlist.class);
		
		return modelMapper.map(wishlistRepository.save(wishlist),WishlistDto.class);
	}

	@Override
	public WishlistDto updateWishlist(Integer id, WishlistDto wishlistDto) {
		
		return null;
	}

	@Override
	public void deleteWishlist(Integer id) {
		Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(()->new RuntimeException("Wishlist not found"));
		wishlistRepository.delete(wishlist);
	}

	@Override
	public WishlistDto getWishlistById(Integer id) {
		Wishlist wishlist = wishlistRepository.findById(id).orElseThrow(()->new RuntimeException("Wishlist not found"));
		return modelMapper.map(wishlist, WishlistDto.class);
	}

	@Override
	public List<WishlistDto> getWishlistByUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		List<Wishlist> list = wishlistRepository.findByUser(user);
		return list.stream().map((w)->modelMapper.map(w, WishlistDto.class)).toList();
	}

	@Override
	public List<WishlistDto> getAllWishlist() {
		List<Wishlist> list = wishlistRepository.findAll();
		return list.stream().map((w)->modelMapper.map(w, WishlistDto.class)).toList();
	}

}
