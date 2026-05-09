package com.learn.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.User;
import com.learn.ecommerce.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
	
	List<Wishlist> findByUser(User user);

}
