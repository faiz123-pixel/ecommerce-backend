package com.learn.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.enumes.OrderStatus;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	
	List<Orders> findByOrderStatus(OrderStatus orderStatus);

}
