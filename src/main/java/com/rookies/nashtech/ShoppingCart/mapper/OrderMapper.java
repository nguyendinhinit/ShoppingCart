package com.rookies.nashtech.ShoppingCart.mapper;

import org.springframework.stereotype.Component;
import com.rookies.nashtech.ShoppingCart.dto.OrderDTO;
import com.rookies.nashtech.ShoppingCart.entity.Order;

@Component
public class OrderMapper {
  public OrderDTO fromEntity(Order order) {
    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setId(order.getId());
    orderDTO.setUserId(order.getUser().getId());
    orderDTO.setDateCreated(order.getDateCreated());
    orderDTO.setTotalCost(order.getTotalCost());
    orderDTO.setState(order.getState());
    return orderDTO;
  }
}
