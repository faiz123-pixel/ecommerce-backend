package com.learn.ecommerce.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.CartsDto;
import com.learn.ecommerce.entities.Carts;
import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.repositories.CartsRepository;
import com.learn.ecommerce.repositories.ProductRepository;
import com.learn.ecommerce.repositories.UserRepository;
import com.learn.ecommerce.services.CartsService;

@Service
public class CartsServiceImpl implements CartsService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private CartsRepository cartsRepository;
	
	@Override
	public CartsDto addCart(CartsDto cartsDto) {
		Carts carts = modelMapper.map(cartsDto, Carts.class);
		
		
		return modelMapper.map(cartsRepository.save(carts), CartsDto.class);
	}

	@Override
	public CartsDto updateCart(CartsDto cartsDto, Integer id) {
		Carts cart = cartsRepository.findById(id).orElseThrow(()->new RuntimeException("Cart no found"));
		
		cart.setQuantity(cartsDto.getQuantity());
		cart.setTotalPrice(cartsDto.getTotalPrice());
		
		
		return modelMapper.map(cartsRepository.save(cart), CartsDto.class);
	}

	@Override
	public List<CartsDto> getAllCart() {
		List<Carts> list = cartsRepository.findAll();
		
		return list.stream().map(c->modelMapper.map(c, CartsDto.class)).toList();
	}

	@Override
	public CartsDto getCartById(Integer id) {
		Carts cart = cartsRepository.findById(id).orElseThrow(()->new RuntimeException("Cart not found"));
		return modelMapper.map(cart, CartsDto.class);
	}

	@Override
	public void deleteCart(Integer id) {
		Carts cart = cartsRepository.findById(id).orElseThrow(()->new RuntimeException("cart not found"));
		cartsRepository.delete(cart);
		
	}

	@Override
	public List<CartsDto> getCartByUser(String id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));

		List<Carts> list = cartsRepository.findByUser(user);
		
		return list.stream().map(c->modelMapper.map(c, CartsDto.class)).toList();
	}

}
