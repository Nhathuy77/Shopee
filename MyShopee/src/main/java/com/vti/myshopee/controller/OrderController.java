package com.vti.myshopee.controller;

import com.vti.myshopee.model.dto.AccountCreateDTO;
import com.vti.myshopee.model.dto.OrderCreateDTO;
import com.vti.myshopee.model.entity.Account;
import com.vti.myshopee.model.entity.Order;
import com.vti.myshopee.model.entity.OrderStatus;
import com.vti.myshopee.service.iml.AccountService;
import com.vti.myshopee.service.iml.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("api/v1/order")
    @CrossOrigin("*")
    public class OrderController {
        @Autowired
        private OrderService orderService;

        @PreAuthorize("hasAnyAuthority('USER')")
        @PostMapping("/create")
        public Order create(@RequestBody OrderCreateDTO dto) {
            return orderService.create(dto);

        }


        @PreAuthorize("hasAnyAuthority('USER')")
        @PostMapping("/buy/{id}")
        public Order buy(@PathVariable int id) {
            return orderService.buyProduct(id);

        }
        @PreAuthorize("hasAnyAuthority('USER')")
        @PostMapping("/cancel/{id}")
        public Order cancel(@PathVariable int id) {
            return orderService.cancelOrder(id);

        }
    }