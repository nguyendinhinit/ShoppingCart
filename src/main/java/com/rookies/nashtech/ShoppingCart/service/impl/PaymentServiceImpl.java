package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    @Autowired
    public PaymentServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public OrderDTO deliveredOrder(Integer id) {
        Order order = orderRepository.getById(id);
        order.setState("Delivered");
        orderRepository.save(order);
        return orderMapper.fromEntity(order);
    }

    @Override
    public OrderDTO completeOrder(Integer id) {
        Order order = orderRepository.getById(id);
        order.setState("Completed");
        return orderMapper.fromEntity(order);
    }
}