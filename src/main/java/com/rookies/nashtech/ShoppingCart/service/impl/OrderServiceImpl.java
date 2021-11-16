package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.common.BaseConstants;
import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.exception.OrderNotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
  }

  @Transactional
  @Override
  public List<OrderDTO> getAllOrders() {
    return orderRepository.findAll().stream().map(orderMapper::fromEntity).collect(Collectors.toList());
  }

  @Transactional
  @Override
  public OrderDTO getOrderById(int orderId) {
    Order order = orderRepository.findOrderById(orderId);
    return orderMapper.fromEntity(order);
  }

  @Transactional
  @Override
  public OrderDTO payOrderById(int orderId) {
    Order currentOrder = orderRepository.findOrderById(orderId);
    if (currentOrder == null) {
      throw new OrderNotFoundException("No order with id: " + orderId);
    }
    currentOrder.setState(BaseConstants.ORDER_PAID);
    orderRepository.save(currentOrder);
    return orderMapper.fromEntity(currentOrder);
  }

  @Override
  public OrderDTO createOrder(OrderPayload payload) {


    return null;
  }

  @Override
  public void deleteOrderById(int orderId) {

  }
}
