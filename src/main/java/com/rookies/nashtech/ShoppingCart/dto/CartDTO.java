package com.rookies.nashtech.ShoppingCart.dto;

/***
 * DTO of {@link Cart} entity model
 * 
 * @author ManhTuan
 */
import com.rookies.nashtech.ShoppingCart.entity.Product;
import lombok.Data;

@Data
public class CartDTO {
  private Integer id;
  private Integer userId;
  private Product product;
  private int quantity;
}
