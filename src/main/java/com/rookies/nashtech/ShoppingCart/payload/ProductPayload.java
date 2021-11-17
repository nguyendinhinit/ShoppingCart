package com.rookies.nashtech.ShoppingCart.payload;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ProductPayload {
  private int id;
  private Double price;
  private int quantity;


  public ProductPayload() {

  }

  public ProductPayload(int id, Double price, int quantity) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
