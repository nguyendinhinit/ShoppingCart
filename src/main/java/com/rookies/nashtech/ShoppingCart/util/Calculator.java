package com.rookies.nashtech.ShoppingCart.util;

import com.rookies.nashtech.ShoppingCart.payload.ProductPayload;

import java.util.List;

public final class Calculator {
  public static float getTotalCost(List<ProductPayload> products) {
    float totalCost = 0.0f;
    for (ProductPayload payload : products) {
      totalCost+=payload.getPrice() * payload.getQuantity();
    }
    return totalCost;
  }
}
