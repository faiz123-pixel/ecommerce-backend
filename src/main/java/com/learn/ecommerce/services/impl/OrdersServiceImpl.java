package com.learn.ecommerce.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ecommerce.dtos.OrdersDto;
import com.learn.ecommerce.entities.Orders;
import com.learn.ecommerce.enumes.OrderStatus;
import com.learn.ecommerce.repositories.OrdersRepository;
import com.learn.ecommerce.services.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public OrdersDto placeOrder(OrdersDto ordersDto) {
		Orders orders = modelMapper.map(ordersDto, Orders.class);
		orders.setOrderStatus(OrderStatus.PENDING);
        orders.setStatus(true);
		Orders savedOrder = ordersRepository.save(orders);
		return modelMapper.map(savedOrder, OrdersDto.class);
	}

	@Override
	public List<OrdersDto> getAllOrders() {
		List<Orders> orders = ordersRepository.findAll();
		return orders.stream().map((order)->modelMapper.map(order, OrdersDto.class)).toList();
	}

	@Override
    public List<OrdersDto> getOrdersByStatus(String status) {
        OrderStatus orderStatus;

        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid order status");
        }

        List<Orders> orders = ordersRepository.findByOrderStatus(orderStatus);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrdersDto.class))
                .toList();
    }

	@Override
    public OrdersDto updateOrderStatus(Integer orderId, String status) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus newStatus;

        try {
            newStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid order status");
        }

        order.setOrderStatus(newStatus);

        Orders updatedOrder = ordersRepository.save(order);
        return modelMapper.map(updatedOrder, OrdersDto.class);
    }

	@Override
	public void cancelOrder(Integer orderId) {

	    Orders order = ordersRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    System.out.println("Order Status: " + order.getOrderStatus());

	    if (order.getOrderStatus() == OrderStatus.CANCELLED) {
	        throw new RuntimeException("Order is already cancelled");
	    }

	    if (!order.getStatus()) {
	        throw new RuntimeException("Order is already inactive");
	    }

	    if (order.getOrderStatus() == OrderStatus.SHIPPED) {
	        throw new RuntimeException("Order already shipped, cannot cancel");
	    }

	    if (order.getOrderStatus() == OrderStatus.DELIVERED) {
	        throw new RuntimeException("Order already delivered, cannot cancel");
	    }

	    order.setOrderStatus(OrderStatus.CANCELLED);
	    order.setStatus(false);

	    ordersRepository.save(order);
	}

}
