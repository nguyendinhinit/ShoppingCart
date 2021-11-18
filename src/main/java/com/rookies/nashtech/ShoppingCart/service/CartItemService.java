package com.rookies.nashtech.ShoppingCart.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.payload.CartProductItem;

public interface CartItemService {
  List<CartItemDTO> addToCart(List<CartProductItem> listPayload, HttpServletRequest request);

  CartItemDTO deleteProductInCart(Integer cartId, Integer productId);

  CartItemDTO increaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity);

  CartItemDTO decreaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity);

  CartItemDTO adjustProductQuantityOfCart(Integer cartId, Integer productId, Integer quantity);

  CartItem mapperFromDTO(CartItemDTO payload);

}
