package com.rookies.nashtech.ShoppingCart.service;

import com.rookies.nashtech.ShoppingCart.entity.Order;
import com.rookies.nashtech.ShoppingCart.exception.NotFoundException;
import com.rookies.nashtech.ShoppingCart.exception.UserNotFoundException;
import com.rookies.nashtech.ShoppingCart.mapper.OrderMapper;
import com.rookies.nashtech.ShoppingCart.payload.OrderPayload;
import com.rookies.nashtech.ShoppingCart.repository.OrderRepository;
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
  OrderRepository orderRepository;

  @Mock
  UserRepository userRepository;

  @Mock
  OrderMapper orderMapper;

  @Mock
  OrderAggregationService orderAggregationService;

  @InjectMocks
  OrderServiceImpl underTest;

  @Test
  public void testGetOrderByIdGivenInvalidIdShouldThrowNotFoundException() {
    NotFoundException exception = assertThrows(NotFoundException.class, () -> underTest.getOrderById(1));
    assertEquals("No order of id 1", exception.getMessage());
    verify(orderMapper, never()).fromEntity(any(Order.class));
  }

  @Test
  public void testGetAllOrdersShouldCallOrderAggregationService2Times() {
    Order first = new Order();
    Order second = new Order();
    List<Order> orders = Arrays.asList(first, second);
    when(orderRepository.findAll()).thenReturn(orders);
    underTest.getAllOrders();
    verify(orderAggregationService, times(2)).aggregate(any()); // verify mapper call 2 times
  }

  @Nested
  @DisplayName("Test: Create Order")
  class TestCreateOrder {
    @Test
    public void testCreateOrderGivenInvalidUsernameShouldThrowException() {
      OrderPayload mockPayload = mock(OrderPayload.class);
      Mockito.when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
      UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> underTest.createOrder(mockPayload));
      assertEquals("No user of username null", exception.getMessage());
      verify(orderRepository, never()).findOrderById(anyInt());
    }
  }
}
