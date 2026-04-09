package com.learn.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learn.ecommerce.dtos.CategoriesDto;
import com.learn.ecommerce.services.CategoriesService;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoriesDto> addCategory(@RequestBody CategoriesDto dto) {
        return ResponseEntity.ok(categoriesService.addCategory(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriesDto> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoriesDto dto) {

        return ResponseEntity.ok(categoriesService.updateCategory(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriesDto>> getAllCategories() {
        return ResponseEntity.ok(categoriesService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriesDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriesService.getCategoryById(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
    	categoriesService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted");
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Integer id) {
        categoriesService.deactivateCategory(id);
        return ResponseEntity.ok("Category deactivated successfully");
    }
    
    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activate(@PathVariable Integer id) {
        categoriesService.activateCategory(id);
        return ResponseEntity.ok("Category activated successfully");
    }
    
    
}