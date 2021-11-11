package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.common.BaseConstants;
import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.exception.OrderNotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int orderId) {
        Order order = orderRepository.findOrderById(orderId);
        return orderMapper.fromEntity(order);
    }

    @Override
    public OrderDTO payOrder(int orderId) {
        Order currentOrder = orderRepository.findOrderById(orderId);
        if (currentOrder == null) {
            throw new OrderNotFoundException("No order with id: " + orderId);
        }
        currentOrder.setState(BaseConstants.ORDER_PAID);
        orderRepository.save(currentOrder);
        return orderMapper.fromEntity(currentOrder);
    }
}
