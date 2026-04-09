package com.learn.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.CategoriesDto;
import com.learn.ecommerce.entities.Categories;
import com.learn.ecommerce.repositories.CategoriesRepository;
import com.learn.ecommerce.services.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoriesRepository categoriesRepository;

	@Override
	public CategoriesDto addCategory(CategoriesDto categoriesDto) {
		Categories categories = modelMapper.map(categoriesDto, Categories.class);
		Categories savedCategories = categoriesRepository.save(categories);
		return modelMapper.map(savedCategories, CategoriesDto.class);
	}

	// ✅ Update Category
	@Override
	public CategoriesDto updateCategory(Integer id, CategoriesDto categoriesDto) {
		Categories existingCategory = categoriesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		// Update fields
		existingCategory.setCategoryName(categoriesDto.getCategoryName());
		existingCategory.setDescription(categoriesDto.getDescription());
		existingCategory.setStatus(categoriesDto.getStatus());

		Categories updatedCategory = categoriesRepository.save(existingCategory);

		return modelMapper.map(updatedCategory, CategoriesDto.class);
	}

	@Override
	public void deactivateCategory(Integer id) {
		Categories category = categoriesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		category.setStatus(false);
		categoriesRepository.save(category);
	}

	@Override
	public void deleteCategory(Integer id) {
		Categories category = categoriesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
		categoriesRepository.delete(category);
	}
	
	@Override
	public void activateCategory(Integer id) {
		Categories category = categoriesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		category.setStatus(true);
		categoriesRepository.save(category);
	}
	
	@Override
	public List<CategoriesDto> getAllCategories() {
		List<Categories> categoriesList = categoriesRepository.findAll();

		return categoriesList.stream()
				.map(category -> modelMapper.map(category, CategoriesDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoriesDto getCategoryById(Integer id) {
		Categories category = categoriesRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

		return modelMapper.map(category, CategoriesDto.class);
	}
}