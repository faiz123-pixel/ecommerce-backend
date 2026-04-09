package com.learn.ecommerce.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.ProductDto;
import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.repositories.ProductRepository;
import com.learn.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product savedProduct = productRepository.save(product);
		return modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Integer id, ProductDto productDto) {
		Product existingProduct = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		existingProduct.setProductName(productDto.getProductName());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setSKU(productDto.getSKU());
		existingProduct.setInventoryCount(productDto.getInventoryCount());
		existingProduct.setStatus(productDto.getStatus());

		if(productDto.getCategoryId() != null){
			existingProduct.setCategoryId(
				modelMapper.map(productDto.getCategoryId(), 
				com.learn.ecommerce.entities.Categories.class)
			);
		}

		Product updatedProduct = productRepository.save(existingProduct);
		return modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> products = productRepository.findAll();

		return products.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}


	@Override
	public void deleteProduct(Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		productRepository.delete(product);
	}

	@Override
	public void deactivateProduct(Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		product.setStatus(false);
		productRepository.save(product);
	}
	
	@Override
	public void activateProduct(Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		product.setStatus(true);
		productRepository.save(product);
	}

	@Override
	public ProductDto getProductById(Integer id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

		return modelMapper.map(product, ProductDto.class);
	}
}