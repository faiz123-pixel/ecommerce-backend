package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.CategoriesDto;

public interface CategoriesService {

	CategoriesDto addCategory(CategoriesDto categoriesDto);
	
	CategoriesDto updateCategory(Integer id, CategoriesDto categoriesDto);
	
	void deactivateCategory(Integer id);
	
	void activateCategory(Integer id);
	
	void deleteCategory(Integer id);
	
	List<CategoriesDto> getAllCategories();
	
	CategoriesDto getCategoryById(Integer id);
	
	
}
