package com.learn.ecommerce.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.ShippingDto;
import com.learn.ecommerce.entities.Shipping;
import com.learn.ecommerce.enumes.ShippingStatus;
import com.learn.ecommerce.repositories.ShippingRepository;
import com.learn.ecommerce.services.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService{
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	
	@Autowired ModelMapper modelMapper;

	@Override
	public ShippingDto addShipping(ShippingDto shippingDto) {
		Shipping shipping = new Shipping();

	    shipping.setOrders(shippingDto.getOrders());

	    shipping.setCourierService(shippingDto.getCourierService());
	    shipping.setShippingStatus(ShippingStatus.SHIPPED);
	    shipping.setShippingCost(shippingDto.getShippingCost());

	    shipping.setTrackingNumber(shippingDto.getTrackingNumber());
		return modelMapper.map(shippingRepository.save(shipping), ShippingDto.class);
	}

	@Override
	public List<ShippingDto> getAllShipping() {
		List<Shipping> list = shippingRepository.findAll();
		return list.stream().map((s)->modelMapper.map(s,ShippingDto.class)).toList();
	}

	@Override
	public ShippingDto updateShipping(Integer id, ShippingDto shippingDto) {
		Shipping shipping = shippingRepository.findById(id).orElseThrow(()->new RuntimeException("Shipping not found"));
		shipping.setCourierService(shippingDto.getCourierService());
		shipping.setShippingStatus(shippingDto.getShippingStatus());
		shipping.setTrackingNumber(shippingDto.getTrackingNumber());
		shipping.setShippingCost(shippingDto.getShippingCost());
		return modelMapper.map(shippingRepository.save(shipping), ShippingDto.class);
	}

	@Override
	public void deleteShipping(Integer id) {
		Shipping shipping = shippingRepository.findById(id).orElseThrow(()->new RuntimeException("Shipping not found"));
		shippingRepository.delete(shipping);
		
	}

}
