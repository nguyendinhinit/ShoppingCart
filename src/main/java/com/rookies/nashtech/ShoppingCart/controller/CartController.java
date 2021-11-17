package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.service.CartItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class CartController {
  private final CartItemService cartItemService;

  @Autowired
  public CartController(CartItemService cartService) {
    this.cartItemService = cartService;
  }

  @ApiOperation(value = "Add Product to Cart", authorizations = {@Authorization(value = "jwtToken")}, response = CartDTO.class)
  @PostMapping(value = "/cart", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO payload) throws NotFoundException {
    CartItemDTO createdCart = cartItemService.addToCart(payload);
    return ResponseEntity.ok(createdCart);
  }
}
