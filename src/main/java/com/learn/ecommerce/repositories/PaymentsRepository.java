package com.learn.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, Integer>{

}
