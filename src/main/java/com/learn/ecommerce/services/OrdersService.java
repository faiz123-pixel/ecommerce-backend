package com.learn.ecommerce.services;

import java.util.List;

import com.learn.ecommerce.dtos.OrdersDto;
import com.learn.ecommerce.entities.User;


public interface OrdersService {


    OrdersDto placeOrder(OrdersDto ordersDto);

    List<OrdersDto> getAllOrders();

    List<OrdersDto> getOrdersByStatus(String status);
    
    List<OrdersDto> getOrdersByUser(String id);
    
    OrdersDto updateOrderStatus(Integer orderId, String status);

    void cancelOrder(Integer orderId);
}