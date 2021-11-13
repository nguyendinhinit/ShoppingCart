package com.rookies.nashtech.ShoppingCart.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.mapper.CartMapper;
import com.rookies.nashtech.ShoppingCart.repository.CartRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.CartService;
import javassist.NotFoundException;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

  private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

  private final CartMapper mapper;
  private final UserRepository userRepository;
  private final CartRepository cartRepository;

  public CartServiceImpl(CartMapper mapper, UserRepository userRepository, CartRepository cartRepository) {
    this.userRepository = userRepository;
    this.cartRepository = cartRepository;
    this.mapper = mapper;
  }

  /**
   * Check the existence of a shopping cart belonging to the current user. If not exist, create a new one.
   * 
   * @param user User object input
   * @throws NotFoundException If user not found with User input
   * @throws IllegalArgumentException if User input is null
   * @return An exist Entity Cart or Cart just created
   * 
   * @author ManhTuan
   */
  @Override
  public Cart cartCheck(User user) throws NotFoundException {
    if (user == null) {
      throw new IllegalArgumentException("Username cannot be null!");
    }
    Optional<Cart> optionalCart = cartRepository.findByUser(user);
    if (!optionalCart.isPresent()) {
      return createCart(user);
    }
    return optionalCart.get();
  }

  /**
   * Create Cart for current User login
   * 
   * @param username User object input
   * @throws NotFoundException If User not found
   * @throws IllegarArgumentException If User input is null
   * @return Created Entity Cart
   * 
   * @author ManhTuan
   */
  @Override
  @Transactional
  public Cart createCart(User user) throws NotFoundException {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null!");
    }
    Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
    if (!optionalUser.isPresent()) {
      throw new NotFoundException("User not found with username: " + user);
    }
    Cart cart = new Cart();
    cart.setUser(optionalUser.get());
    return cartRepository.save(cart);
  }

  /**
   * Mapping CartDTO to Cart
   * 
   * @param payload CartDTO needs to mapped
   * @return An Entity Cart
   * 
   * @author ManhTuan
   */
  @Override
  public Cart mapFromDTO(CartDTO payload) {
    Cart cart = new Cart();
    Optional<User> optionalUser = userRepository.findByUsername(payload.getUsername());
    if (optionalUser.isPresent()) {
      cart.setId(payload.getId());
      cart.setUser(optionalUser.get());
    }
    return cart;
  }

}
