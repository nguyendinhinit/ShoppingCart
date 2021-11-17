package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;

import java.util.List;

public interface OrderService {
  OrderDTO payOrderById(Integer orderId);

  List<OrderDTO> getAllOrders();

  OrderDTO getOrderById(Integer orderId);

  OrderDTO createOrder(OrderPayload payload);

  void deleteOrderById(Integer orderId);
}
