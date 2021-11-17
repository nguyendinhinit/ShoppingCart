package com.rookies.nashtech.ShoppingCart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends NotFoundException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
