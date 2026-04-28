package com.learn.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.ecommerce.dtos.CategoryUpdateRequestDto;
import com.learn.ecommerce.dtos.ProductDto;
import com.learn.ecommerce.entities.Categories;
import com.learn.ecommerce.repositories.CategoriesRepository;
import com.learn.ecommerce.services.CategoriesService;
import com.learn.ecommerce.services.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private CategoriesRepository categoriesRepository;


    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Integer id,
            @RequestBody ProductDto productDto) {

        ProductDto updatedProduct = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivateProduct(@PathVariable Integer id) {
        productService.deactivateProduct(id);
        return ResponseEntity.ok("Product deactivated successfully");
    }
    
    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateProduct(@PathVariable Integer id) {
        productService.activateProduct(id);
        return ResponseEntity.ok("Product activated successfully");
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Integer categoryId)
    {
    	Categories categories = categoriesRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category Not Found"));
    	return ResponseEntity.ok(productService.getProductByCategory(categories));
    }
    
    @PutMapping("/category/update")
    public String updateProductCategory(@RequestBody CategoryUpdateRequestDto request) {

        int updatedCount = productService.reassignCategory(
                request.getOldCategoryId(),
                request.getNewCategoryId()
        );

        return updatedCount + " products updated successfully";
    }
}