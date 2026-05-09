package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.CartsDto;

public interface CartsService {
	
	CartsDto addCart(CartsDto cartsDto);
	CartsDto updateCart(CartsDto cartsDto,Integer id);
	List<CartsDto> getAllCart();
	List<CartsDto> getCartByUser(String id);
	CartsDto getCartById(Integer id);
	void deleteCart(Integer id);

}
