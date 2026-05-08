package com.learn.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Carts;
import com.learn.ecommerce.entities.User;

public interface CartsRepository extends JpaRepository<Carts, Integer>{
	
	List<Carts> findByUser(User user);

}
