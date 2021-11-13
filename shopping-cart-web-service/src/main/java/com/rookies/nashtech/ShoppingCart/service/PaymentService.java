package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.entity.Order;

public interface PaymentService {
    void deliveredOrder(Integer id);
    void completeOrder(Integer id);
}
