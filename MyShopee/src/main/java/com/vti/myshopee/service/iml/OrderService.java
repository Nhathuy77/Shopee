package com.vti.myshopee.service.iml;

import com.vti.myshopee.model.dto.OrderCreateDTO;
import com.vti.myshopee.model.entity.Account;
import com.vti.myshopee.model.entity.Order;
import com.vti.myshopee.model.entity.OrderStatus;
import com.vti.myshopee.model.entity.Product;
import com.vti.myshopee.repository.AccountRepository;
import com.vti.myshopee.repository.OrderRepository;
import com.vti.myshopee.repository.ProductRepository;
import com.vti.myshopee.service.IOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOderService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return null;
    }

    @Override
    public Order create(OrderCreateDTO dto) {
        Optional<Account> accountOptional = accountRepository.findById(dto.getAccountId());
        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        if (accountOptional.isPresent() && productOptional.isPresent()) {
            Account account = accountOptional.get();
            Product product = productOptional.get();
            Order order = new Order();
            order.setAccount(account);
            order.setProduct(product);
            order.setQuantity(dto.getQuantity());
            order.setOrderStatus(OrderStatus.PENDING);
            return orderRepository.save(order);
        }

        return null;
    }

    @Override
    public Order buyProduct(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.DONE);
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order cancelOrder(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setOrderStatus(OrderStatus.CANCEL);
            return orderRepository.save(order);
        }
        return null;
    }

}