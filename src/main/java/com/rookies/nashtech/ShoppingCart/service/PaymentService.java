package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;

public interface PaymentService {
    OrderDTO deliveredOrder(Integer id);
    OrderDTO completeOrder(Integer id);
}