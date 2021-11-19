package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface CartItemService {
  CartItemDTO addToCart(CartItemDTO payload, HttpServletRequest request) throws NotFoundException;

  CartItemDTO deleteProductInCart(Integer productId);

  CartItemDTO increaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity);

  CartItemDTO decreaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity);

  CartItemDTO adjustProductQuantityOfCart(Integer productId, Integer quantity);

  CartItem mapperFromDTO(CartItemDTO payload) throws NotFoundException;

}
