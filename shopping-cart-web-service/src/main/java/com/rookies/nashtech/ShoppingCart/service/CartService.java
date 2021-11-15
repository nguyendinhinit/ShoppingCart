package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;
import javassist.NotFoundException;

public interface CartService {
  Cart cartCheck(User user) throws NotFoundException;

  Cart createCart(User user) throws NotFoundException;

  Cart mapFromDTO(CartDTO payload);
}
