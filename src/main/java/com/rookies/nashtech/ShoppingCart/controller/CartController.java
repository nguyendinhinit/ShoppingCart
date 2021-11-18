package com.rookies.nashtech.ShoppingCart.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.payload.CartProductItem;
import com.rookies.nashtech.ShoppingCart.service.CartItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;


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
  public ResponseEntity<List<CartItemDTO>> addToCart(@RequestBody List<CartProductItem> listPayload, HttpServletRequest request) {
    List<CartItemDTO> listCreatedCart = cartItemService.addToCart(listPayload, request);
    return ResponseEntity.ok(listCreatedCart);
  }

  @ApiOperation(value = "Delete Product in Cart", authorizations = {@Authorization(value = "jwtToken")}, response = CartDTO.class)
  @DeleteMapping(value = "/cart/{cartId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> deleteProductInCart(@PathVariable Integer cartId, @RequestParam Integer productId) {
    CartItemDTO cartDeleted = cartItemService.deleteProductInCart(cartId, productId);
    return ResponseEntity.ok(cartDeleted);
  }

  @ApiOperation(value = "Adjust quantity Product in Cart", authorizations = {@Authorization(value = "jwtToken")}, response = CartDTO.class)
  @PutMapping(value = "/cart/{cartId}/", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<CartItemDTO> adjustQuantityProductInCart(@PathVariable Integer cartId, @RequestParam Integer productId, @RequestParam Integer number) {
    CartItemDTO cartUpdated = cartItemService.adjustProductQuantityOfCart(cartId, productId, number);
    return ResponseEntity.ok(cartUpdated);
  }
}
