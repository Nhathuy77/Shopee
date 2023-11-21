package com.vti.myshopee.service;

import com.vti.myshopee.model.dto.OrderCreateDTO;
import com.vti.myshopee.model.entity.Order;
import com.vti.myshopee.model.entity.OrderStatus;

import java.util.List;

public interface IOderService  {
    List<Order> getAll();
    List<Order> findByStatus(OrderStatus status);
    Order create(OrderCreateDTO dto);
    Order buyProduct (int orderId);
    Order cancelOrder (int orderId);


}
