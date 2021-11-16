package com.rookies.nashtech.ShoppingCart.payload;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProductPayload {
  private int id;
  private int quantity;

  public ProductPayload() {

  }

  public ProductPayload(int id, int quantity) {
    this.id = id;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
