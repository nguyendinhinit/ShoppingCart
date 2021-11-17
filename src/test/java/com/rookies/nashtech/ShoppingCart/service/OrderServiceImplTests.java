package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.entity.OrderProduct;
import com.rookies.nashtech.ShoppingCart.entity.Product;
import com.rookies.nashtech.ShoppingCart.entity.User;
import com.rookies.nashtech.ShoppingCart.exception.NotFoundException;
import com.rookies.nashtech.ShoppingCart.exception.UserNotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.repository.OrderProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
import com.rookies.nashtech.ShoppingCart.repository.ProductRepository;
import com.rookies.nashtech.ShoppingCart.repository.UserRepository;
import com.rookies.nashtech.ShoppingCart.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class OrderServiceImplTests {
  @Mock
  UserRepository userRepository;

  @Mock
  ProductRepository productRepository;

  @Mock
  OrderProductRepository orderProductRepository;

  @Mock
  OrderRepository orderRepository;

  @Mock
  OrderMapper orderMapper;

  @InjectMocks
  OrderServiceImpl underTest;

  @Test
  public void testGetOrderByIdGivenInvalidIdShouldThrowNotFoundException() {
    NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.getOrderById(1));
    assertEquals("No order of id 1", exception.getMessage());
    verify(orderMapper, never()).fromEntity(any(Order.class));
  }

  @Test
  public void testGetOrderByIdGivenValidIdShouldReturnCorrectOrder() {
    User mockUser = mock(User.class);
    when(mockUser.getUsername()).thenReturn("dummy");

    Product mockProduct = mock(Product.class);
    when(mockProduct.getId()).thenReturn(1);
    when(mockProduct.getQuantity()).thenReturn(100);

    Order mockOrder = mock(Order.class);
    when(mockOrder.getId()).thenReturn(1);
    when(mockOrder.getUser()).thenReturn(mockUser);

    OrderProduct mockOrderProduct = mock(OrderProduct.class);

    when(mockOrderProduct.getId()).thenReturn(1);
    when(mockOrderProduct.getOrder()).thenReturn(mockOrder);
    when(mockOrderProduct.getProduct()).thenReturn(mockProduct);
    when(mockOrderProduct.getQuantity()).thenReturn(10);

    Mockito.when(userRepository.findByUsername("dummy")).thenReturn(Optional.of(mockUser));
    Mockito.when(productRepository.findProductById(1)).thenReturn(mockProduct);
    Mockito.when(orderRepository.findOrderById(1)).thenReturn(mockOrder);
    Mockito.when(orderProductRepository.findById(1)).thenReturn(Optional.of(mockOrderProduct));

    when(orderMapper.fromEntity(mockOrder)).thenCallRealMethod();
    OrderDTO orderDTO = underTest.getOrderById(1);

    verify(orderRepository, times(1)).findOrderById(1);
    verify(orderMapper, times(1)).fromEntity(mockOrder);

    assertEquals(orderDTO.getId(), mockOrder.getId());
    assertEquals(orderDTO.getUsername(), mockOrder.getUser().getUsername());
  }

  @Test
  public void testGetAllOrdersShouldCallMapper2Times() {
    Order first = new Order();
    Order second = new Order();
    List<Order> orders = Arrays.asList(first, second);
    when(orderRepository.findAll()).thenReturn(orders);
    underTest.getAllOrders();
    verify(orderMapper, times(2)).fromEntity(any()); // verify mapper call 2 times
  }

  @Nested
  @DisplayName("Test: Create Order")
  class TestCreateOrder {
    @Test
    public void testCreateOrderGivenInvalidUsernameShouldThrowException() {
      OrderPayload mockPayload = mock(OrderPayload.class);
      UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> underTest.createOrder(mockPayload));
      assertEquals("No user of username null", exception.getMessage());
      verify(orderRepository, never()).findOrderById(anyInt());
    }
  }
}
