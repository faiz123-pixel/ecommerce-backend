package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);
	
	ProductDto updateProduct(Integer id,ProductDto productDto);
	
	List<ProductDto> getAllProduct();
	
	void deleteProduct(Integer id);
	
	void deactivateProduct(Integer id);
	
	void activateProduct(Integer id);
	
	ProductDto getProductById(Integer id);
	
}
