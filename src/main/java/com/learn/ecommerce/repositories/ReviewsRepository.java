package com.learn.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Product;
import com.learn.ecommerce.entities.Reviews;
import com.learn.ecommerce.entities.User;

public interface ReviewsRepository  extends JpaRepository<Reviews, Integer>{

	List<Reviews> findByUser(User user);
	
	List<Reviews> findByProduct(Product product);
	
}
