package com.rookies.nashtech.ShoppingCart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rookies.nashtech.ShoppingCart.entity.CartItem;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.exception.NotFoundException;
import com.rookies.nashtech.ShoppingCart.payload.CartProductItem;
import com.rookies.nashtech.ShoppingCart.repository.CartItemRepository;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.impl.CartItemServiceImpl;
import com.rookies.nashtech.ShoppingCart.util.JwtUtil;

@ExtendWith(SpringExtension.class)
public class CartItemServiceImplTest {

  private List<CartProductItem> listMockPayload;
  private CartItem mockCartItem;
  private CartProductItem mockPayload;
  private Product mockProduct;

  @BeforeEach
  public void setUP() {
    listMockPayload = new ArrayList<CartProductItem>();
    mockCartItem = mock(CartItem.class);
    mockPayload = mock(CartProductItem.class);
    mockProduct = mock(Product.class);
  }

  @AfterEach
  public void tearDown() {
    listMockPayload.clear();
    mockCartItem = null;
    mockPayload = null;
    mockProduct = null;
  }

  @InjectMocks
  private JwtUtil jwtUtil;

  @Mock
  private CartItemRepository cartItemRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private CartItemServiceImpl underTest;

  @Nested
  @DisplayName("Test Add to cart")
  class TestAddToCart {
    @Test
    public void testGivenPayloadNullShouldThrowException() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.addToCart(listMockPayload, Mockito.any()));
      assertEquals("Request body can not be empty.", exception.getMessage());
    }

    @Test
    public void testGivenNotExistProductIdShouldThrowException() {
      listMockPayload.add(mockPayload);
      when(productRepository.findProductById(any())).thenReturn(null);
      NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.addToCart(listMockPayload, null));
      verify(productRepository, times(1)).findProductById(any());
      assertEquals("Product not found.", exception.getMessage());
    }

    @Test
    public void testGivenInvalidProductQuantityShouldThrowException() {
      Product mockProduct = mock(Product.class);
      when(mockPayload.getQuantity()).thenReturn(-1);
      when(productRepository.findProductById(any())).thenReturn(mockProduct);
      listMockPayload.add(mockPayload);
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.addToCart(listMockPayload, null));
      assertEquals("Invalid quantity.", exception.getMessage());
    }

    @Test
    public void testGivenProductQuantityThatStorageCannotProvideShouldThrowException() {
      Product mockProduct = mock(Product.class);
      when(mockPayload.getQuantity()).thenReturn(99);
      when(mockProduct.getQuantity()).thenReturn(1);
      when(productRepository.findProductById(any())).thenReturn(mockProduct);
      listMockPayload.add(mockPayload);
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.addToCart(listMockPayload, null));
      assertEquals("Exceeded the number of products. The remaining amount: 1", exception.getMessage());
    }

  }

  @Nested
  @DisplayName("Test delete product in cart")
  class testDeleteProductInCart {
    @Test
    public void testGivenCartIdNullShouldThrowException() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.deleteProductInCart(null, 1));
      assertEquals("Cart id can not be null.", exception.getMessage());
    }

    @Test
    public void testGivenProductIdNullShouldThrowException() {
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> underTest.deleteProductInCart(1, null));
      assertEquals("Product id can not be null.", exception.getMessage());
    }

    @Test
    public void testGivenNotExistProductShouldThrowException() {
      when(productRepository.findById(1)).thenReturn(null);
      NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.deleteProductInCart(Mockito.anyInt(), 1));
      assertEquals("Product not found.", exception.getMessage());
      verify(productRepository, times(1)).findProductById(1);
    }

    // @Test
    // public void testDeleteProductShouldWorkCorrectly() {
    // when(productRepository.findProductById(1)).thenReturn(mockProduct);
    // when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
    // underTest.deleteProductInCart(1, 1);
    // }
  }
}
