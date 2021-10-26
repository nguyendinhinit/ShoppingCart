package com.rookies.nashtech.ShoppingCart.dto;

/***
 * DTO of {@link Cart} entity model
 * 
 * @author ManhTuan
 */
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.entity.User;
import lombok.Data;

@Data
public class CartDTO {
  private User cartId;
  private Product product;
  private int quantity;
}
