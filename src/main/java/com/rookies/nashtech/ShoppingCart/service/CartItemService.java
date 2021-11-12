package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import javassist.NotFoundException;

public interface CartItemService {
  CartItemDTO addToCart(CartItemDTO payload) throws NotFoundException;

  CartItemDTO increaseProductQuantityOfCart(Integer cartId, String userId, Integer productId);

  CartItemDTO decreaseProductQuantityOfCart(Integer cartId, String userId, Integer productId);

  CartItem mapperFromDTO(CartItemDTO payload) throws NotFoundException;

}
