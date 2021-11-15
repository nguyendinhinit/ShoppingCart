package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
  /**
   * Map an Entity {@link Cart} to DTO {@link CartDTO}
   *
   * @param cart An Entity Cart needs to be mapped
   * @return {@link CartDTO} mapped from Entity input
   */
  public CartDTO fromEntity(Cart cart) {
    CartDTO dto = new CartDTO();
    dto.setId(cart.getId());
    dto.setUsername(cart.getUser().getUsername());
    return dto;
  }
}
