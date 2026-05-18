package com.learn.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Coupons;

public interface CouponRepository extends JpaRepository<Coupons, Integer> {

	Coupons findByCouponCode(String code);
}
