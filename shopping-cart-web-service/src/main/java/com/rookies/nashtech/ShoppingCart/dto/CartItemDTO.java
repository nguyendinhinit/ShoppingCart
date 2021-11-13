package com.rookies.nashtech.ShoppingCart.dto;

import com.rookies.nashtech.ShoppingCart.entity.Product;
import lombok.Data;

@Data
public class CartItemDTO {
  private Integer id;
  private Integer cartId;
  private Product product;
  private int quantity;
}
