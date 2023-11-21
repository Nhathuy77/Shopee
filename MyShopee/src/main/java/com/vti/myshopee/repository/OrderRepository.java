package com.vti.myshopee.repository;

import com.vti.myshopee.model.entity.Order;
import com.vti.myshopee.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderStatusAndAccount_Id(OrderStatus orderStatus, long accountId);

    List<Order> findAllByAccount_IdOrderByIdDesc(long accountId);
}