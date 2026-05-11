package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.ShippingDto;

public interface ShippingService {

	ShippingDto addShipping(ShippingDto shippingDto);
	List<ShippingDto> getAllShipping();
	ShippingDto updateShipping(Integer id, ShippingDto shippingDto);
	void deleteShipping(Integer id);
}
