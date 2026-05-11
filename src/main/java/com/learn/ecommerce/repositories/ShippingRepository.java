package com.learn.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Integer>{

}
