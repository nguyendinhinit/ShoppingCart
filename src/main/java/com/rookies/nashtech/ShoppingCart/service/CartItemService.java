package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;

public interface CartService {
  CartDTO addToCart(CartDTO payload);
}
