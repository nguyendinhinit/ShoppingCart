package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;

public interface OrderAggregationService {
  OrderPayload aggregate(Order order);
}
