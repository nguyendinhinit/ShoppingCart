package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import org.springframework.stereotype.Component;

/**
 * Mapper between Entity {@link Cart} and DTO {@link CartDTO}
 *
 * @author ManhTuan
 */
@Component
public class CartItemMapper {
  /**
   * Map an Entity {@link CartItem} to DTO {@link CartItemDTO}
   *
   * @param cart An Entity Cart needs to be mapped
   * @return {@link CartItemDTO} mapped from Entity input
   */
  public CartItemDTO fromEntity(CartItem cart) {
    CartItemDTO dto = new CartItemDTO();
    dto.setId(cart.getId());
    dto.setCartId(cart.getCart().getId());
    dto.setProduct(cart.getProduct());
    dto.setQuantity(cart.getQuantity());
    return dto;
  }


}
