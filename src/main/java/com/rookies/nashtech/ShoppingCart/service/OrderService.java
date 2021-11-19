package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;

import java.util.List;

public interface OrderService {
  OrderPayload payOrderById(Integer orderId);

  List<OrderPayload> getAllOrders();

  OrderPayload getOrderById(Integer orderId);

  OrderPayload createOrder(OrderPayload payload);

  void deleteOrderById(Integer orderId);
}
