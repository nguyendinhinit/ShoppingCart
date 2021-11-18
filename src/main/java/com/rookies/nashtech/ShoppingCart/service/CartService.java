package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;

public interface CartService {
  Cart cartCheck(User user);

  Cart createCart(User user);


  Cart mapFromDTO(CartDTO payload);
}
