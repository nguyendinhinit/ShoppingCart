package com.rookies.nashtech.ShoppingCart.payload;

public class CartProductItem {
  Integer productId;
  Integer quantity;

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
