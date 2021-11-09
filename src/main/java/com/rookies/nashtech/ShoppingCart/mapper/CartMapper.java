package com.rookies.nashtech.ShoppingCart.mapper;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;
import org.springframework.stereotype.Component;

/**
 * Mapper between Entity {@link Cart} and DTO {@link CartDTO}
 * 
 * @author ManhTuan
 *
 */
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
    dto.setUserId(cart.getUser().getId());
    dto.setProduct(cart.getProduct());
    dto.setQuantity(cart.getQuantity());
    return dto;
  }

  /**
   * Map a DTO {@link CartDTO} to Entity {@link Cart}
   * 
   * @param payload CartDTO which needs to be mapped
   * @return {@link Cart} mapped from DTO input
   */
  public Cart fromDTO(CartDTO payload) {
    return fromDTO(new Cart(), payload);
  }

  public Cart fromDTO(Cart newCart, CartDTO payload) {
    User user = new User();
    user.setId(payload.getId());
    newCart.setId(payload.getId());
    newCart.setUser(user);
    newCart.setProduct(payload.getProduct());
    newCart.setQuantity(payload.getQuantity());
    return newCart;
  }
}
