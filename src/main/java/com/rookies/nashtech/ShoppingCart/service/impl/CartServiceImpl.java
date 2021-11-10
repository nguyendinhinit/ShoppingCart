package com.rookies.nashtech.ShoppingCart.service.impl;

import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.mapper.CartMapper;
import com.rookies.nashtech.ShoppingCart.repository.CartRepository;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.CartService;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Business logic of Cart.
 * 
 * @author ManhTuan
 *
 */
@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

  private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

  private final CartRepository cartRepository;
  private final CartMapper mapper;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final ProductService productService;

  @Autowired
  public CartServiceImpl(CartRepository cartRepository, CartMapper mapper, UserRepository userRepository, ProductRepository productRepository, ProductService productService) {
    this.cartRepository = cartRepository;
    this.mapper = mapper;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.productService = productService;
  }

  /**
   * Add Product to Cart
   * 
   * @param payload the {@link CartDTO} input
   * @return CartDTO just added
   * @throws IllegalArgumentException if payload input is {@code null}, {@code invalid} or {@code null} User, Product, or {@code invalid} Quantity
   * 
   */
  @Override
  @Transactional
  public CartDTO addToCart(CartDTO payload) {

    logger.info("Payload null check");
    if (payload == null) {
      logger.error("Payload is null");
      throw new IllegalArgumentException("Request body can not be null.");
    }

    // logger.info("Cart ID null check");
    // if (payload.getId() == null) {
    // logger.error("Cart ID is null");
    // throw new IllegalArgumentException("Cart ID (User) can not be null.");
    // }

    logger.info("Product null check");
    if (payload.getProduct() == null) {
      logger.error("Product is null");
      throw new IllegalArgumentException("Product can not be null.");
    }

    logger.info("Verify Quantity value: " + payload.getQuantity());
    if (payload.getQuantity() < 0) {
      logger.error("Quantity is invalid with value: " + payload.getQuantity());
      throw new IllegalArgumentException("Invalid quantity.");
    }

    logger.info("Verify Cart ID with User ID: " + payload.getUserId());
    Optional<User> optionalUser = userRepository.findById(payload.getUserId());
    if (optionalUser.isEmpty()) {
      logger.error("User not found with id : " + payload.getUserId());
      throw new IllegalArgumentException("User not found.");
    }

    logger.info("Verify Product with Product ID: " + payload.getProduct().getId());
    Product product = productRepository.findProductById(payload.getProduct().getId());
    if (product == null) {
      logger.error("Product not found with id : " + payload.getProduct().getId());
      throw new IllegalArgumentException("Product not found.");
    }

    Cart newCart = new Cart();
    newCart.setProduct(payload.getProduct());
    newCart.setQuantity(payload.getQuantity());
    newCart.setUser(optionalUser.get());
    logger.info("Create Cart.");
    Cart createdCart = cartRepository.save(newCart);

    productService.decreaseProductQuantity(payload.getProduct().getId(), payload.getQuantity());

    logger.info("Cart created with Cart ID: " + payload.getId());
    return mapper.fromEntity(createdCart);
  }


}
