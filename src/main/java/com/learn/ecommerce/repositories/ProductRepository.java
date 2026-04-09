package com.learn.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
