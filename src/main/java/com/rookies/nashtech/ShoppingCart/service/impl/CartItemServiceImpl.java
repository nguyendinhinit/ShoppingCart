package com.rookies.nashtech.ShoppingCart.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rookies.nashtech.ShoppingCart.dto.CartDTO;
import com.rookies.nashtech.ShoppingCart.dto.CartItemDTO;
import com.rookies.nashtech.ShoppingCart.entity.Cart;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.exception.NotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.CartItemMapper;
import com.rookies.nashtech.ShoppingCart.payload.CartProductItem;
import com.rookies.nashtech.ShoppingCart.repository.CartItemRepository;
import com.rookies.nashtech.ShoppingCart.repository.CartRepository;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.CartItemService;
import com.rookies.nashtech.ShoppingCart.service.CartService;
import com.rookies.nashtech.ShoppingCart.service.ProductService;
import com.rookies.nashtech.ShoppingCart.util.JwtUtil;

/**
 * Business logic of Cart.
 *
 * @author ManhTuan
 */
@Service
@Transactional(readOnly = true)
public class CartItemServiceImpl implements CartItemService {

  private final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

  private final CartService cartService;
  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final CartItemMapper mapper;
  private final ProductRepository productRepository;
  private final ProductService productService;
  private final JwtUtil jwtUtil;

  @Autowired
  public CartItemServiceImpl(CartService cartService, CartRepository cartRepository, CartItemRepository cartItemRepository, CartItemMapper mapper, UserRepository userRepository, ProductRepository productRepository, ProductService productService, JwtUtil jwtUtil) {
    this.cartService = cartService;
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.mapper = mapper;
    this.productRepository = productRepository;
    this.productService = productService;
    this.jwtUtil = jwtUtil;
  }

  /**
   * Add Product to Cart
   *
   * @param listPayload the List {@link CartDTO} input
   * @param request HttpServletRequest
   * @return CartDTO just added
   * @throws NotFoundException
   * @throws IllegalArgumentException if payload input is {@code null}, {@code invalid} or {@code null} User, Product, or {@code invalid} Quantity
   */
  @Override
  @Transactional
  public List<CartItemDTO> addToCart(List<CartProductItem> listPayload, HttpServletRequest request) {

    logger.info("List payload check");
    if (listPayload.isEmpty()) {
      logger.error("List payload is empty");
      throw new IllegalArgumentException("Request body can not be empty.");
    }
    List<CartItem> listCartItem = new ArrayList<CartItem>();
    for (CartProductItem payload : listPayload) {
      logger.info("Verify Product with Product ID: " + payload.getProductId());
      Product product = productRepository.findProductById(payload.getProductId());
      if (product == null) {
        logger.error("Product not found with id : " + payload.getProductId());
        throw new NotFoundException("Product not found.");
      }

      logger.info("Verify Quantity value: " + payload.getQuantity());
      if (payload.getQuantity() < 0) {
        logger.error("Quantity is invalid with value: " + payload.getQuantity());
        throw new IllegalArgumentException("Invalid quantity.");
      }


      logger.info("Compare to quantity of product;");
      if (payload.getQuantity() > product.getQuantity()) {
        logger.error("Exceeded the number of products. Current: " + payload.getQuantity() + ". Actual: " + product.getQuantity());
        throw new IllegalArgumentException("Exceeded the number of products. The remaining amount: " + product.getQuantity());
      }


      User user = jwtUtil.getUser(request);

      Cart cart = cartService.cartCheck(user);

      CartItem cartItem = new CartItem();
      cartItem.setCart(cart);
      cartItem.setProduct(product);
      cartItem.setQuantity(payload.getQuantity());

      logger.info("Create CartItem.");
      CartItem cartItemCreated = cartItemRepository.save(cartItem);

      productService.decreaseProductQuantity(product, payload.getQuantity());

      logger.info("CartItem created with Cart ID: " + payload.getProductId());
      listCartItem.add(cartItemCreated);
    }

    return listCartItem.stream().map(cart -> mapper.fromEntity(cart)).collect(Collectors.toList());
  }

  @Override
  @Transactional
  public CartItemDTO deleteProductInCart(Integer cartId, Integer productId) {
    logger.info("Cart id null check");
    if (cartId == null) {
      logger.error("Cart id is null");
      throw new IllegalArgumentException("Cart id can not be null.");
    }

    logger.info("Product id null check");
    if (productId == null) {
      logger.error("Product id is null");
      throw new IllegalArgumentException("Product id can not be null.");
    }

    logger.info("Verify Product with Product ID: " + productId);
    Product product = productRepository.findProductById(productId);
    if (product == null) {
      logger.error("Product not found with id : " + productId);
      throw new NotFoundException("Product not found.");
    }

    Optional<CartItem> cartItem = cartItemRepository.findById(cartId);
    cartItemRepository.delete(cartItem.get());
    productService.increaseProductQuantity(product, cartItem.get().getQuantity());
    return mapper.fromEntity(cartItem.get());
  }

  @Override
  @Transactional
  public CartItemDTO adjustProductQuantityOfCart(Integer cartId, Integer productId, Integer quantity) {
    logger.info("Cart id null check");
    if (cartId == null) {
      logger.error("Cart id is null");
      throw new IllegalArgumentException("Cart id can not be null.");
    }

    logger.info("Product id null check");
    if (productId == null) {
      logger.error("Product id is null");
      throw new IllegalArgumentException("Product id can not be null.");
    }

    logger.info("Verify Product with Product ID: " + productId);
    Product product = productRepository.findProductById(productId);
    if (product == null) {
      logger.error("Product not found with id : " + productId);
      throw new NotFoundException("Product not found.");
    }

    logger.info("Compare to quantity of product;");
    if (quantity > product.getQuantity()) {
      logger.error("Exceeded the number of products. Current: " + quantity + ". Actual: " + product.getQuantity());
      throw new IllegalArgumentException("Exceeded the number of products. The remaining amount: " + product.getQuantity());
    }

    Optional<CartItem> cartItem = cartItemRepository.findById(cartId);
    CartItemDTO cartItemDTO = new CartItemDTO();
    if (quantity > 0) {
      cartItemDTO = increaseProductQuantityOfCart(product, cartItem.get(), quantity);
    }
    if (quantity < 0) {
      cartItemDTO = decreaseProductQuantityOfCart(product, cartItem.get(), quantity);
    }
    return cartItemDTO;
  }

  /**
   * Increase number of product in Cart
   * 
   * @return CartItemDTO after augment
   */
  @Override
  @Transactional
  public CartItemDTO increaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity) {
    Integer newQuantity = cartItem.getQuantity() + quantity;
    cartItem.setQuantity(newQuantity);
    CartItem cartItemAdjustedQuantity = cartItemRepository.save(cartItem);
    productService.decreaseProductQuantity(product, quantity);
    return mapper.fromEntity(cartItemAdjustedQuantity);
  }

  /**
   * Decrease number of product in Cart
   * 
   * @return CartItemDTO after reduce
   * @throws IllegalArgumentException
   */
  @Override
  @Transactional
  public CartItemDTO decreaseProductQuantityOfCart(Product product, CartItem cartItem, Integer quantity) {
    if (cartItem.getQuantity() < Math.abs(quantity)) {
      logger.error("The number of products you want to reduce is more than the quantity in the cart. Quantity you want to decrease: " + Math.abs(quantity) + ". Actual in your Cart: " + cartItem.getQuantity());
      throw new IllegalArgumentException("The number of products you want to reduce is more than the quantity in the cart. Quantity you want to decrease: " + Math.abs(quantity) + ". Actual in your Cart: " + cartItem.getQuantity());
    }
    Integer newQuantity = cartItem.getQuantity() + quantity;
    cartItem.setQuantity(newQuantity);
    CartItem cartItemAdjustedQuantity = cartItemRepository.save(cartItem);
    productService.increaseProductQuantity(product, Math.abs(quantity));
    return mapper.fromEntity(cartItemAdjustedQuantity);
  }

  /**
   * Map a DTO {@link CartItemDTO} to Entity {@link CartItem}
   *
   * @param payload CartItemDTO which needs to be mapped
   * @return {@link Cart} mapped from DTO input
   */
  @Override
  public CartItem mapperFromDTO(CartItemDTO payload) {
    CartItem cartItem = new CartItem();
    Optional<Cart> cart = cartRepository.findById(payload.getCartId());
    if (!cart.isPresent()) {
      throw new NotFoundException("Cart not found with Cart ID: " + payload.getCartId());
    }
    cartItem.setId(payload.getId());
    cartItem.setCart(cart.get());
    cartItem.setProduct(payload.getProduct());
    cartItem.setQuantity(payload.getQuantity());
    return cartItem;
  }



}
