package com.learn.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Integer>{

}
