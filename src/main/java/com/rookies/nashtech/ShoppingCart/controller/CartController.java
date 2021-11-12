package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.service.CartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.service.CartItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/v1")
public class CartController {
  private final CartItemService cartItemService;

  @Autowired
  public CartController(CartItemService cartService) {
    this.cartItemService = cartService;
  }

  @ApiOperation(value = "Add Product to Cart", response = CartDTO.class)
  @PostMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO payload) throws NotFoundException {
    CartItemDTO createdCart = cartItemService.addToCart(payload);
    return ResponseEntity.ok(createdCart);
  }
}
