package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;

    @Autowired
    public PaymentServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void deliveredOrder(Integer id) {
        Order order = orderRepository.getById(id);
        order.setState("Delivered");
        orderRepository.save(order);
    }

    @Override
    public void completeOrder(Integer id) {
        Order order = orderRepository.getById(id);
        order.setState("Completed");
        orderRepository.save(order);
    }
}
