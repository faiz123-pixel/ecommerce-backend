package com.learn.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learn.ecommerce.entities.Categories;
import com.learn.ecommerce.entities.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByCategoryId(Categories categorieId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.categoryId.categoryId = :newId WHERE p.categoryId.categoryId = :oldId")
	int updateCategory(@Param("oldId") Integer oldId, @Param("newId") Integer newId);
	

}
