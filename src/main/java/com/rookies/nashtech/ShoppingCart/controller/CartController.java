package com.rookies.nashtech.ShoppingCart.controller;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.service.CartItemService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
  public ResponseEntity<CartItemDTO> addToCart(@RequestBody CartItemDTO payload, HttpServletRequest request) throws NotFoundException {
    CartItemDTO createdCart = cartItemService.addToCart(payload, request);
    return ResponseEntity.ok(createdCart);
  }

  @ApiOperation(value = "Delete Product in Cart", response = CartDTO.class)
  @DeleteMapping(value = "/cart/productId{}", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> deleteProductInCart(@RequestParam Integer productId) {
    CartItemDTO cartDeleted = cartItemService.deleteProductInCart(productId);
    return ResponseEntity.ok(cartDeleted);
  }

  @ApiOperation(value = "Adjust quantity Product in Cart", response = CartDTO.class)
  @PutMapping(value = "/cart/productId{}number{}", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> adjustQuantityProductInCart(@RequestParam Integer productId, @RequestParam Integer number) {
    CartItemDTO cartUpdated = cartItemService.adjustProductQuantityOfCart(productId, number);
    return ResponseEntity.ok(cartUpdated);
  }
}
