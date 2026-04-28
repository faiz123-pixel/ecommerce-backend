package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.ProductDto;
import com.learn.ecommerce.entities.Categories;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);
	
	ProductDto updateProduct(Integer id,ProductDto productDto);
	
	List<ProductDto> getAllProduct();
	
	List<ProductDto> getProductByCategory(Categories category);
	
	void deleteProduct(Integer id);
	
	void deactivateProduct(Integer id);
	
	void activateProduct(Integer id);
	
	ProductDto getProductById(Integer id);
	
	public int reassignCategory(Integer oldCategoryId, Integer newCategoryId);
	
}
