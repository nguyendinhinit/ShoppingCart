package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;

import java.util.List;

public interface OrderService {
  OrderDTO payOrderById(int orderId);

  List<OrderDTO> getAllOrders();

  OrderDTO getOrderById(int orderId);

  OrderDTO createOrder(OrderPayload payload);

  void deleteOrderById(int orderId);
}
