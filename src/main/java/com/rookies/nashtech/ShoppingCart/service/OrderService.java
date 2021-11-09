package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO payOrder(int orderId);

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(int orderId);
}
